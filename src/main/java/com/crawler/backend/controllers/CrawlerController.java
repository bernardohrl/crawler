package com.crawler.backend.controllers;

import com.crawler.backend.exceptions.InvalidKeywordException;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.services.CrawlerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.NoSuchElementException;

import static com.crawler.backend.utils.Constants.*;
import static spark.Spark.*;

public class CrawlerController {

    public CrawlerController() {}
    public CrawlerService service = new CrawlerService();
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public void init() {
        get(GET_ENDPOINT, (req, res) -> gson.toJson(service.getCrawl(req.params("id"))));

        post(POST_ENDPOINT, (req, res) -> {
            PostRequestBody body = gson.fromJson(req.body(), PostRequestBody.class);

            return gson.toJson(service.crawl(body));
        });


        // Exception Handler
        exception(InvalidKeywordException.class, (e, request, response) -> {
            response.status(400);
            response.body(INVALID_KEYWORD_MESSAGE);
        });

        exception(RuntimeException.class, (e, request, response) -> {
            response.status(500);
            response.body(UNEXPECTED_ERROR + e.getMessage());
        });

        exception(NoSuchElementException.class, (e, request, response) -> {
            response.status(404);
            response.body(ID_NOT_FOUND_MESSAGE);
        });
    }
}
