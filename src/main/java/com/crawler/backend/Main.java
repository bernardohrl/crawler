package com.crawler.backend;

import com.crawler.backend.controllers.CrawlerController;
import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Spark.port(4567);

        CrawlerController controller = new CrawlerController();
        controller.init();

    }
}
