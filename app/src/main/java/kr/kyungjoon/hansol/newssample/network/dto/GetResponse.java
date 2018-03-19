package kr.kyungjoon.hansol.newssample.network.dto;

import java.util.List;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class GetResponse {

    public final List<Articles> articles;
    public final String status;
    public final int totalResults;

    public GetResponse(List<Articles> articles, String status, int totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
