package com.example.benja.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleList {
    @SerializedName("results")
    private List<Article> articles;
    private String copyright;
    private String last_updated;

    public ArticleList(List<Article> articles, String copyright, String last_updated) {
        this.articles = articles;
        this.copyright = copyright;
        this.last_updated = last_updated;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getLast_updated() {
        return last_updated;
    }
}
