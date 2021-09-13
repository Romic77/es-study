package com.example;


import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.116.136", 9200, "http")));

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("1001").doc(XContentType.JSON, "sex", "女");

        DocWriteResponse.Result result = client.update(updateRequest, RequestOptions.DEFAULT).getResult();
        System.out.println(result);
        client.close();
    }
}
