package com.crawler.backend.services;

import com.crawler.backend.exceptions.InvalidKeywordException;
import com.crawler.backend.helper.IdGenerator;
import com.crawler.backend.models.Run;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.models.enums.Status;
import com.crawler.backend.utils.Constants;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.crawler.backend.utils.Constants.MAX_KEYWORD_LENGTH;
import static com.crawler.backend.utils.Constants.MIN_KEYWORD_LENGTH;

public class CrawlerService {

    HashMap<String, Run> runs = new HashMap<>();

    public Run getCrawl(String id) {
        return Optional.ofNullable(runs.get(id)).orElseThrow();
    }

    public PostResponseBody crawl(PostRequestBody body) throws InterruptedException {
        String keyword = body.getKeyword();
        String baseUrl  = Constants.BASE_URL;
        String id = IdGenerator.getId();

        if(isKeywordValid(keyword))
            throw new InvalidKeywordException();


        Run newRun = new Run(id, Status.ACTIVE);
        runs.put(id, newRun);



        CompletableFuture.runAsync(() -> {
            newRun.addUrl("URL1");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            newRun.addUrl("URL2");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            newRun.setStatus(Status.DONE);
        });



        return new PostResponseBody(id);
    }

    private boolean isKeywordValid (String keyword) {
        return (keyword.length() < MIN_KEYWORD_LENGTH) || (keyword.length() > MAX_KEYWORD_LENGTH);
    }
}
