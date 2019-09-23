package com.youxiu326;

import com.youxiu326.entity.Article;
import com.youxiu326.repositories.ArticleRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbDataElasticsearchDemoApplicationTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void contextLoads() {

        // 创建索引，并配置映射关系
        //template.createIndex(Article.class);

        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setId(i++);
            article.setTitle("中华小当家"+i);
            article.setContent("中国是一个文明的多好多好多好"+i);

            //articleRepository.index(article);
            articleRepository.save(article);
        }
    }

    @Test
    public void testFindAll() {
        Iterable<Article> articles = articleRepository.findAll();
        articles.forEach(it-> System.out.println(it));
    }

    @Test
    public void testFindByTitle() {
        List<Article> articles = articleRepository.findByTitle("中华小当家1");
        articles.forEach(it-> System.out.println(it));
    }

    @Test
    public void testFindByTitleOrContent() {

        Pageable pageable = PageRequest.of(0, 15);

        //List<Article> articles = articleRepository.findByTitleOrContent("中华小当家1","哔哩哔哩哔哩哔哔哩13");
        List<Article> articles = articleRepository.findByTitleOrContent("中华小当家2","哔哩哔哩哔哩哔哔哩13",pageable);
        articles.forEach(it-> System.out.println(it));

        /**
         * 可以对搜索的内容先分词然后再进行查询，每个词之间都是 and 的关系
         * 例如 中华小当家 既然包含中华 也得包含小当家
         */
    }

    /**
     * 使用原生查询
     * @throws Exception
     */
    @Test
    public void testNativeSearchQuery() throws  Exception{

        // 创建一个查询对象
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery("中华小当家2").defaultField("title"))
                .withPageable(PageRequest.of(0,15))
                .build();

        // 执行查询
        List<Article> articles = template.queryForList(query, Article.class);
        articles.forEach(it-> System.out.println(it));
    }


    @Test
    public void searchCity() {
        // 分页参数
        Pageable pageable = PageRequest.of(0, 100);

        String pinyin="中华";

        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                boolQuery()
                        .should(boolQuery()
                                .must(queryStringQuery("*地名*").defaultField("type").boost(8))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
                        .should(boolQuery()
                                .must(queryStringQuery("*公园*").defaultField("type").boost(10))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
                        .should(boolQuery()
                                .must(queryStringQuery("*广场*").defaultField("type").boost(10))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
                        .should(boolQuery()
                                .must(queryStringQuery("*风景名胜*").defaultField("type").boost(10))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
                        .should(boolQuery()
                                .must(queryStringQuery("*交通*").defaultField("type").boost(5))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
                        .should(boolQuery()
                                .must(queryStringQuery("*餐厅*").defaultField("type").boost(3))
                                .must(queryStringQuery(pinyin).defaultField("name_pinyin")))
        );

        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();


        Page<Article> searchPageResults = articleRepository.search(searchQuery);
        List<Article> content = searchPageResults.getContent();
        System.out.println(content);
    }


    /**
     * 高亮查询 报错了
     */
    @Test
    public void testNativeSearchQueryHighlight(){

        String fild="content";
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery("中国").defaultField(fild))
                .withHighlightFields(new HighlightBuilder.Field(fild))
                .build();
        AggregatedPage<Article> list =  template.queryForPage(nativeSearchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Article> films = new ArrayList<>();
                SearchHits hits = response.getHits();
                for (SearchHit hit:hits){
                    if(hits.getHits().length<=0){
                        return null;
                    }
                    Article film=new Article();
                    String hightLightMessage = hit.getHighlightFields().get(fild).fragments()[0].toString();
                    film.setId(Long.parseLong(hit.getId()));
                    film.setTitle(hit.getSource().get("title").toString());
                    film.setContent(hit.getSource().get("content").toString());

                    try {
                        String setMethodName = parSetName(fild);
                        Class<? extends Article> filmClass = film.getClass();
                        Method setMethod = filmClass.getMethod(setMethodName,String.class);
                        setMethod.invoke(film,hightLightMessage);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    films.add(film);

                }
                if (films.size()>0){
                    return new AggregatedPageImpl<>((List<T>) films);
                }
                return null;
            }
        });

        list.getContent().forEach(film -> System.out.println(film));
    }



    public String parSetName(String fieldName){
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

}
