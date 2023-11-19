package com.crawler.backend.utils;

public class Constants {
    public static final String BASE_URL = (!System.getenv("BASE_URL").isBlank()) ? System.getenv("BASE_URL") : "http://hiring.axreng.com/";

    public static final String POST_ENDPOINT = "/crawl";
    public static final String GET_ENDPOINT = "/crawl/:id";
}
