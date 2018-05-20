package kr.kyungjoon.hansol.newssample.network.dto;


import java.io.Serializable;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class Articles implements Serializable{

    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final String publishedAt;
    private final Source source;

    public Articles(String author, String title, String description, String url, String urlToImage, String publishedAt, Source source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }
}
