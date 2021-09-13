package com.example;


import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTest_Doc_Delete {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.116.136", 9200, "http")));

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user").id("1001");

        System.out.println(client.delete(deleteRequest, RequestOptions.DEFAULT).getResult());

        client.close();
    }
}
