package com.crawler.backend.models;

import com.google.gson.annotations.Expose;

public class PostResponseBody {
    @Expose
    String id;

    public PostResponseBody(String id) {
        this.id = id;
    }
}
