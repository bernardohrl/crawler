package com.crawler.backend.models;

import com.crawler.backend.models.enums.Status;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Run {
    @Expose
    String id;

    @Expose
    Status status;

    @Expose
    List<String> matchUrls = new ArrayList<>();

    List<String> previousUrls = new ArrayList<>();


    public Run(String id) {
        this.id = id;
        this.status = Status.ACTIVE;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addMatchUrl(String url) {
        this.matchUrls.add(url);
    }


    public void addPreviousUrl(String previousUrl) {
        this.previousUrls.add(previousUrl);
    }

    public List<String> getPreviousUrls() {
        return previousUrls;
    }
}
