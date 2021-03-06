package com.example.benja.myapplication.Utils.Search_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("ALL")
public class SearchArticle {

    private String web_url;
    private String snippet;
    @SerializedName("abstract") String description;
    private String pub_date;
    private List<SearchArticleImages> multimedia;

    public SearchArticle(String web_url, String snippet, String description, String pub_date, List<SearchArticleImages> multimedia) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.description = description;
        this.pub_date = pub_date;
        this.multimedia = multimedia;
    }

    public String getWeb_url() {
        return web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getDescription() {
        return description;
    }

    public String getPub_date() {
        return pub_date;
    }

    public List<SearchArticleImages> getMultimedia() {
        return multimedia;
    }
}
