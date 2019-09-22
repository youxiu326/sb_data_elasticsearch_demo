package com.youxiu326;

import com.youxiu326.entity.Article;
import com.youxiu326.repositories.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbDataElasticsearchDemoApplicationTests {

    @Autowired
    private ArticleRepository articleRepository;

    //@Autowired
    //private ElasticsearchTemplate template;

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
        List<Article> articles = articleRepository.findByTitleOrContent("中华小当家1","哔哩哔哩哔哩哔哔哩13",pageable);
        articles.forEach(it-> System.out.println(it));
    }

}
