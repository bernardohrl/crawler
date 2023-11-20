package com.crawler.backend.models;

import com.crawler.backend.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Run {
    String id;
    Status status;
    List<String> urls = new ArrayList<>();

    public Run(String id) {
        this.id = id;
        this.status = Status.ACTIVE;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addUrl(String url) {
        this.urls.add(url);
    }
}
