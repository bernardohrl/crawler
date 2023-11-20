package com.crawler.backend.models;

import com.crawler.backend.models.enums.Status;

import java.util.List;

public class GetResponseBody {
    String id;
    Status status;
    List<String> urls;

    public GetResponseBody(String id, Status status, List<String> urls) {
        this.id = id;
        this.status = status;
        this.urls = urls;
    }
}
