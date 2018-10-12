package com.example.benja.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularArticleList {
    @SerializedName("results")
    private List<PopularArticle> articles;
    private String copyright;


    public PopularArticleList(List<PopularArticle> articles, String copyright) {
        this.articles = articles;
        this.copyright = copyright;
    }

    public List<PopularArticle> getArticles() {
        return articles;
    }

    public String getCopyright() {
        return copyright;
    }
}
