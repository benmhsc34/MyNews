package com.example.benja.myapplication.Utils.Custom_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomArticleList {
    @SuppressWarnings("CanBeFinal")
    @SerializedName("results")
    private List<CustomArticle> articles;
    @SuppressWarnings("CanBeFinal")
    private String copyright;

    public List<CustomArticle> getArticles() {
        return articles;
    }

}
