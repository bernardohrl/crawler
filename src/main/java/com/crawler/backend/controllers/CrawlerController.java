package com.crawler.backend.controllers;

import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.services.CrawlerService;
import com.crawler.backend.utils.Constants;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class CrawlerController {

    public CrawlerService service = new CrawlerService();
    public CrawlerController() {}

    public void init() {
        get(Constants.GET_ENDPOINT, (req, res) -> new Gson().toJson(service.getCrawl(req.params("id")))
        );

        post(Constants.POST_ENDPOINT, (req, res) -> {
            PostRequestBody body = new Gson().fromJson(req.body(), PostRequestBody.class);

            return new Gson().toJson(service.crawl(body));
        });
    }


}
