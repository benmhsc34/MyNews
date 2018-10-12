package com.example.benja.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularArticle {
    String url;
    String section;
    String title;
    @SerializedName("abstract") String description;
    String published_date;
    List<PopularArticleImagesFolder> media;

    public PopularArticle(String url, String section, String title, String description, String published_date, List<TopArticleImages> multimedia) {
        this.url = url;
        this.section = section;
        this.title = title;
        this.description = description;
        this.published_date = published_date;
        this.media = media;
    }

    public String getUrl() {
        return url;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublished_date() {
        return published_date;
    }

    public List<PopularArticleImagesFolder> getMultimedia() {
        return media;
    }
}
