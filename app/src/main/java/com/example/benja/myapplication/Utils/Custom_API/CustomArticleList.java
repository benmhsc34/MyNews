package com.example.benja.myapplication.Utils.Custom_API;

import com.example.benja.myapplication.Utils.Top_API.TopArticle;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomArticleList {
    @SerializedName("results")
    private List<CustomArticle> articles;
    private String copyright;

    public CustomArticleList(List<CustomArticle> articles, String copyright) {
        this.articles = articles;
        this.copyright = copyright;
    }

    public List<CustomArticle> getArticles() {
        return articles;
    }

    public String getCopyright() {
        return copyright;
    }
}
