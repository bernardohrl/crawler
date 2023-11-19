package com.crawler.backend;

import com.crawler.backend.utils.Constants;
import spark.Spark;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Spark.port(4567);

        get("/crawl/:id", (req, res) -> {
                System.out.println(Constants.BASE_URL);
                return     "GET /crawl/" + req.params("id") + Constants.BASE_URL;
                }
            );

        post("/crawl", (req, res) ->
                "POST /crawl" + System.lineSeparator() + req.body());
    }
}
