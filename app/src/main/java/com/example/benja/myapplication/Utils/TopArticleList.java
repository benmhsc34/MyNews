package com.example.benja.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArticleList {
    @SerializedName("results")
    private List<TopArticle> articles;
    private String copyright;

    public TopArticleList(List<TopArticle> articles, String copyright) {
        this.articles = articles;
        this.copyright = copyright;
    }

    public List<TopArticle> getArticles() {
        return articles;
    }

    public String getCopyright() {
        return copyright;
    }


}
