package kr.kyungjoon.hansol.newssample.network.dto;


import java.io.Serializable;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class Articles implements Serializable{

    public final String author;
    public final String title;
    public final String description;
    public final String url;
    public final String urlToImage;
    public final String publishedAt;
    public final Source source;

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
