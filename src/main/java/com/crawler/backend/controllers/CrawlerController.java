package com.crawler.backend.controllers;

import com.crawler.backend.exceptions.InvalidKeywordException;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.services.CrawlerService;
import com.crawler.backend.utils.Constants;
import com.google.gson.Gson;

import static com.crawler.backend.utils.Constants.*;
import static spark.Spark.*;

public class CrawlerController {

    public CrawlerService service = new CrawlerService();
    public CrawlerController() {}

    public void init() {
        get(GET_ENDPOINT, (req, res) -> new Gson().toJson(service.getCrawl(req.params("id"))));

        post(POST_ENDPOINT, (req, res) -> {
            PostRequestBody body = new Gson().fromJson(req.body(), PostRequestBody.class);

            return new Gson().toJson(service.crawl(body));
        });


        // Exception Handler
        exception(InvalidKeywordException.class, (e, request, response) -> {
            response.status(400);
            response.body(INVALID_KEYWORD_MESSAGE);
        });
    }
}
