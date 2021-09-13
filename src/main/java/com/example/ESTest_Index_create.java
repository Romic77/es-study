package com.example;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Index_create {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.116.136", 9200, "http")));

        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        //添加映射关系-字段默认为text类型 ,聚合时需要进一步设置 fielddata
        request.mapping("{\n" +
                "    \"properties\":{\n" +
                "        \"name\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":true\n" +
                "        },\n" +
                "        \"sex\":{\n" +
                "            \"type\":\"keyword\",\n" +
                "            \"index\":true\n" +
                "        },\n" +
                "        \"age\":{\n" +
                "            \"type\":\"keyword\",\n" +
                "            \"index\":true\n" +
                "        }\n" +
                "    }\n" +
                "}", XContentType.JSON);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("索引操作:" + response.isAcknowledged());
        client.close();
    }
}
