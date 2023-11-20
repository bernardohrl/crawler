package com.crawler.backend.services;

import com.crawler.backend.models.GetResponseBody;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.models.enums.Status;
import com.crawler.backend.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CrawlerService {

    public GetResponseBody getCrawl(String id) {

        return new GetResponseBody(id, Status.ACTIVE, List.of("URL1", "URL2"));
    }

    public PostResponseBody crawl(PostRequestBody body) {
        String baseUrl  = Constants.BASE_URL;
        String keyword = body.getKeyword();
        System.out.println("POST /crawl/  "  + baseUrl + "   " + keyword);

        return new PostResponseBody("12345678");
    }
}
