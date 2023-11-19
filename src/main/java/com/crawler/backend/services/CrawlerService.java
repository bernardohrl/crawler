package com.crawler.backend.services;

import com.crawler.backend.utils.Constants;

public class CrawlerService {

    public String getCrawl(String id) {
        return "GET /crawl/" + id;
    }

    public String crawl(String body) {
        String baseUrl  = Constants.BASE_URL;
        return "POST /crawl/  "  + baseUrl + "   " + body;
    }
}
