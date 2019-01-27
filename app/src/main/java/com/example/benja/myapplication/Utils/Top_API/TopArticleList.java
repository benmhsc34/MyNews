package com.example.benja.myapplication.Utils.Top_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArticleList {
    @SerializedName("results")
    private List<TopArticle> articles;
    private String copyright;

    public List<TopArticle> getArticles() {
        return articles;
    }


}
