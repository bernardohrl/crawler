package com.crawler.backend.models;

import com.google.gson.annotations.Expose;

public class PostRequestBody {
    @Expose
    String keyword;

    public String getKeyword() {
        return keyword;
    }
}
