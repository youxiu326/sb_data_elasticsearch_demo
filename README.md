# sb_data_elasticsearch_demo
sb data elasticsearch 学习例子




http://localhost:8080/swagger-ui.html



`
安装Elasticsearch-SQL Site chrome扩展，使用更方便的查询方式：
1).下载扩展ZIP包：https://github.com/shi-yuan/elasticsearch-sql-site-chrome
2).解压。
3).在chrome浏览器输入：chrome://extensions/
4).开启开发者模式。
5).加载已解压的扩展程序。
6).使用时点击扩展图标即可，可以将 SQL 语句翻译成 DSL
`

`
官网示例
https://github.com/NLPchina/elasticsearch-sql

Aggregations
https://github.com/NLPchina/elasticsearch-sql/wiki/Aggregations

博客学习
https://blog.csdn.net/wenwen513/article/details/85163168

查看索引mapping
http://49.235.105.251:9200/product_index/_mapping?pretty

elasticsearch sql 查询语句

 SELECT COUNT(*),SUM(price),MIN(price), MAX(price),AVG(price) FROM product_index/product

http://49.235.105.251:9200/_sql?sql=select * FROM product_index/product

http://49.235.105.251:9200/_sql/_explain?sql=select * FROM product_index/product

http://49.235.105.251:9200/_sql?sql=select price FROM product_index/product  order by price

`