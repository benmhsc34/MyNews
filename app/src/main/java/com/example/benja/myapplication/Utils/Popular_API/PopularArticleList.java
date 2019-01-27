package com.example.benja.myapplication.Utils.Popular_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularArticleList {
    @SerializedName("results")
    private List<PopularArticle> articles;
    private String copyright;


    public List<PopularArticle> getArticles() {
        return articles;
    }

}
