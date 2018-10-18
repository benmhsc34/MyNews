package com.example.benja.myapplication.Utils.Custom_API;

import com.example.benja.myapplication.Utils.Top_API.TopArticleImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomArticle {

    private String section;
    @SerializedName("abstract")
    private
    String description;
    private String title;
    private String url;
    private String published_date;
    private List<CustomArticleImagesFolder> media;

    public CustomArticle(String section, String description, String title, String url, String published_date, List<CustomArticleImagesFolder> media) {
        this.section = section;
        this.description = description;
        this.title = title;
        this.url = url;
        this.published_date = published_date;
        this.media = media;
    }

    public String getSection() {
        return section;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublished_date() {
        return published_date;
    }

    public List<CustomArticleImagesFolder> getMedia() {
        return media;
    }
}
