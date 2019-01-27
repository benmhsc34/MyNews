package com.example.benja.myapplication.Utils.Custom_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("CanBeFinal")
public class CustomArticle {

    private String section;
    @SuppressWarnings("CanBeFinal")
    @SerializedName("abstract")
    private
    String description;
    private String title;
    private String url;
    private String published_date;
    private List<CustomArticleImagesFolder> media;

    public String getSection() {
        return section;
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
