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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.crawler.backend.utils.Constants.MAX_KEYWORD_LENGTH;
import static com.crawler.backend.utils.Constants.MIN_KEYWORD_LENGTH;

public class CrawlerService {

    HashMap<String, Run> runs = new HashMap<>();

    public Run getCrawl(String id) {
        return Optional.ofNullable(runs.get(id)).orElseThrow();
    }

    public PostResponseBody crawl(PostRequestBody body) {
        String keyword = body.getKeyword();
        String id = IdGenerator.getId();

        if(isKeywordValid(keyword))
            throw new InvalidKeywordException();

        Run newRun = new Run(id);
        runs.put(id, newRun);

        CompletableFuture.runAsync(() -> readAndAddIfMatches(keyword, Constants.BASE_URL, newRun));

        return new PostResponseBody(id);
    }

    private boolean isKeywordValid (String keyword) {
        return (keyword.length() < MIN_KEYWORD_LENGTH) || (keyword.length() > MAX_KEYWORD_LENGTH);
    }

    private void readAndAddIfMatches(String keyword, String url, Run newRun) {
            StringBuilder buffer;
            try {
                InputStream is = new URL(url).openStream();
                buffer = new StringBuilder();

                int ptr;
                while (!((ptr = is.read()) == -1))
                    buffer.append((char)ptr);

            } catch (IOException e) {
                newRun.setStatus(Status.ERROR);
                throw new RuntimeException(e);
            }

            String html = buffer.toString().toLowerCase();
            if(html.contains(keyword.toLowerCase()))
                newRun.addMatchUrl(url);

            List<String> urlsOnPage = getUrlsOnPage(html);

            List<String> newUrls = urlsOnPage.stream()
                    // Process only if contains the same base URL
                    .filter(urlOnPage -> urlOnPage.contains(Constants.BASE_URL))
                    // Not processed before
                    .filter(urlOnPage -> !newRun.getPreviousUrls().contains(urlOnPage))
                    .collect(Collectors.toList());

            newUrls.forEach(newUrl ->  {
                readAndAddIfMatches(keyword, newUrl, newRun);
                newRun.addPreviousUrl(newUrl);
            });

            newRun.setStatus(Status.DONE);

    }

    private List<String> getUrlsOnPage(String html) {
        Pattern linkPattern = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
        Matcher pageMatcher = linkPattern.matcher(html);

        List<String> links = new ArrayList<>();
        while(pageMatcher.find()){
            links.add(pageMatcher.group());
        }

        return links;
    }
}
