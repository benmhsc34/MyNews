package com.example.benja.myapplication.Utils.Custom_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"ALL", "unused"})
public class CustomArticle {

    @SuppressWarnings("unused")
    private String section;
    @SuppressWarnings({"CanBeFinal", "unused"})
    @SerializedName("abstract")
    private
    String description;
    @SuppressWarnings("unused")
    private String title;
    @SuppressWarnings("unused")
    private String url;
    @SuppressWarnings("unused")
    private String published_date;
    @SuppressWarnings("unused")
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
