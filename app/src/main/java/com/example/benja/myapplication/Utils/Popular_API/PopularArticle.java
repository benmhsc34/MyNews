package com.example.benja.myapplication.Utils.Popular_API;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PopularArticle {

    private String url;
    private String section;
    private String title;
    @SerializedName("abstract")
    private String description;
    private String published_date;
    private List<PopularArticleImagesFolder> media;


    public String getUrl() {
        return url;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getPublished_date() {
        return published_date;
    }

    public List<PopularArticleImagesFolder> getMedia() {
        return media;
    }

}

