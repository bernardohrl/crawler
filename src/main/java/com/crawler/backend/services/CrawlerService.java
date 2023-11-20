package com.crawler.backend.services;

import com.crawler.backend.exceptions.InvalidKeywordException;
import com.crawler.backend.helper.IdGenerator;
import com.crawler.backend.models.Run;
import com.crawler.backend.models.PostRequestBody;
import com.crawler.backend.models.PostResponseBody;
import com.crawler.backend.models.enums.Status;
import com.crawler.backend.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.crawler.backend.utils.Constants.MAX_KEYWORD_LENGTH;
import static com.crawler.backend.utils.Constants.MIN_KEYWORD_LENGTH;

public class CrawlerService {

    HashMap<String, Run> runs = new HashMap<>();

    public Run getCrawl(String id) {
        return Optional.ofNullable(runs.get(id)).orElseThrow();
    }

    public PostResponseBody crawl(PostRequestBody body) {
        String keyword = body.getKeyword();
        String baseUrl  = Constants.BASE_URL;
        String id = IdGenerator.getId();

        if(isKeywordValid(keyword))
            throw new InvalidKeywordException();


        Run newRun = new Run(id);
        runs.put(id, newRun);

        CompletableFuture.runAsync(() -> {

            StringBuilder buffer;
            try {
                InputStream is = new URL(baseUrl).openStream();
                buffer = new StringBuilder();

                int ptr;
                while (!((ptr = is.read()) == -1))
                    buffer.append((char)ptr);

            } catch (IOException e) {
                newRun.setStatus(Status.ERROR);
                throw new RuntimeException(e);
            }

            if(buffer.toString().toLowerCase().contains(keyword.toLowerCase()))
                newRun.addUrl(baseUrl);

            newRun.setStatus(Status.DONE);
        });



        return new PostResponseBody(id);
    }

    private boolean isKeywordValid (String keyword) {
        return (keyword.length() < MIN_KEYWORD_LENGTH) || (keyword.length() > MAX_KEYWORD_LENGTH);
    }
}
