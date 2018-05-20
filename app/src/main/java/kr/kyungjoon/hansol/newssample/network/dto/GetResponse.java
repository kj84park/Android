package kr.kyungjoon.hansol.newssample.network.dto;

import java.util.List;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class GetResponse {

    private final List<Articles> articles;
    private final String status;
    private final int totalResults;

    public GetResponse(List<Articles> articles, String status, int totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
