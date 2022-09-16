package com.localsearch.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.localsearch.models.BusinessEntry;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class RestApiService {

    @Autowired
    CacheService cacheService;

    private final Logger logger = LogManager.getLogger(RestApiService.class);
    private final String LOCAL_STORAGE_ENTRY_URL = "https://storage.googleapis.com/coding-session-rest-api/%s";

    public String getEntry(String id) {
        System.out.println("from rest");
        System.out.println("from rest");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format(LOCAL_STORAGE_ENTRY_URL, id))
                .method("GET", null)
                .build();
        try {
            Response response             = client.newCall(request).execute();
            assert response.body() != null : "local storage response body is null;";
            return response.body().string();
        } catch (IOException e) {
            logger.error("IOException: local storage request could not have been performed");
            return null;
        }
    }


//    OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//    MediaType mediaType = MediaType.parse("text/plain");
//    RequestBody body = RequestBody.create(mediaType, "");
//    Request request = new Request.Builder()
//            .url("")
//            .method("GET", body)
//            .build();
//    Response response = client.newCall(request).execute();
}
