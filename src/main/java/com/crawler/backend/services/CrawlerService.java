package com.crawler.backend.services;

import com.crawler.backend.exceptions.InvalidKeywordException;
import com.crawler.backend.models.GetResponseBody;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.models.enums.Status;
import com.crawler.backend.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.crawler.backend.utils.Constants.MAX_KEYWORD_LENGTH;
import static com.crawler.backend.utils.Constants.MIN_KEYWORD_LENGTH;

public class CrawlerService {

    public GetResponseBody getCrawl(String id) {
        return new GetResponseBody(id, Status.ACTIVE, List.of("URL1", "URL2"));
    }

    public PostResponseBody crawl(PostRequestBody body) {
        String baseUrl  = Constants.BASE_URL;
        String keyword = body.getKeyword();
        System.out.println("POST /crawl/  "  + baseUrl + "   " + keyword);

        if(isKeywordValid(keyword))
            throw new InvalidKeywordException();

        return new PostResponseBody("12345678");
    }

    private boolean isKeywordValid (String keyword) {
        return (keyword.length() < MIN_KEYWORD_LENGTH) || (keyword.length() > MAX_KEYWORD_LENGTH);
    }
}
