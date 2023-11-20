package com.crawler.backend.utils;

public class Constants {

    // URLs and Endpoints
    public static final String BASE_URL = (!System.getenv("BASE_URL").isBlank()) ? System.getenv("BASE_URL") : "http://hiring.axreng.com/";
    public static final String POST_ENDPOINT = "/crawl";
    public static final String GET_ENDPOINT = "/crawl/:id";


    // Keyword Validation
    public static final int MIN_KEYWORD_LENGTH = 4;
    public static final int MAX_KEYWORD_LENGTH = 32;
    public static final String INVALID_KEYWORD_MESSAGE = "Invalid Keyword: It should be between 4 and 32 characters.";

    // General
    public static final String UNEXPECTED_ERROR = "Unexpected Error: ";
    public static final String ID_NOT_FOUND_MESSAGE = "Invalid ID: No run found with this ID.";

}
