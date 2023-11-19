package com.crawler.backend.controllers;

import com.crawler.backend.services.CrawlerService;
import com.crawler.backend.utils.Constants;

import static spark.Spark.get;
import static spark.Spark.post;

public class CrawlerController {

    public CrawlerService service = new CrawlerService();
    public CrawlerController() {}

    public void init() {
        get(Constants.GET_ENDPOINT, (req, res) -> {
                    return service.getCrawl(req.params("id"));
                }
        );

        post(Constants.POST_ENDPOINT, (req, res) -> {
                    return service.crawl(req.body());
                }
        );
    }


}
