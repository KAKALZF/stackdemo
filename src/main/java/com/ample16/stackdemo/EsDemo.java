package com.ample16.stackdemo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.junit.Test;

public class EsDemo {
    //    @Before
    public RestClient getClient() {
//        System.out.println("before");
        RestClient client = RestClient.builder(new HttpHost("127.0.0.1", 9200),
                new HttpHost("127.0.0.1", 9201)).build();
        return client;
    }

    @Test
    public void createIndex() {
        try {
            RestClient client = RestClient.builder(new HttpHost("127.0.0.1", 9200),
                    new HttpHost("127.0.0.1", 9201)).build();
            CreateIndexRequest blog = new CreateIndexRequest("blog");
//            XContentBuilder xContentBuilder = new XContentBuilder();
//            xContentBuilder.startObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
