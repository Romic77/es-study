package com.example;


import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTest_Doc_Query {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.116.136", 9200, "http")));


       /* SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        //查询所有数据
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/


        /*//条件查询 age=50
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age",50)));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/


        /*//分页查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        //(当前页码-1)*每页数据
        builder.from(0);
        builder.size(2);
        builder.sort("age", SortOrder.ASC);
        searchRequest.source(builder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/


        /*//分页查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] excludes={"age"};
        String[] includes={"name","sex"};
        builder.fetchSource(includes,excludes);

        searchRequest.source(builder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/

        /*//多条件查询关键字是boolQuery
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gt(20));
        boolQuery.mustNot(QueryBuilders.matchQuery("sex","男"));

        searchRequest.source(builder.query(boolQuery));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/

        /*//模糊查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name","wangwu").fuzziness(Fuzziness.ONE);
        searchRequest.source(builder.query(queryBuilder));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/

        /*//高亮查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zhangsan");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");
        builder.highlighter(highlightBuilder);
        searchRequest.source(builder.query(termQueryBuilder));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/


        /* //聚合查询- 需要到日志查看记录
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder= AggregationBuilders.max("maxAge").field("age");
        builder.aggregation(aggregationBuilder);
        searchRequest.source(builder);


        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });*/

        //分组聚合
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //sexGroup 分组名称-任意命名, filed:sex, 按照sex分组;
        AggregationBuilder aggregationBuilder= AggregationBuilders.terms("sexGroup").field("sex");
        builder.aggregation(aggregationBuilder);
        searchRequest.source(builder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);

        response.getHits().forEach(hit -> {
            System.out.println(hit.getSourceAsString());
        });

        client.close();
    }
}
