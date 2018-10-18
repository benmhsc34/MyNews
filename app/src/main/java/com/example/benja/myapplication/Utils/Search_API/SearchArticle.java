package com.example.benja.myapplication.Utils.Search_API;

import com.google.gson.annotations.SerializedName;

public class SearchArticle {

    private String web_url;
    private String snippet;
    @SerializedName("abstract") String description;
    private String pub_date;

    public SearchArticle(String web_url, String snippet, String description, String pub_date) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.description = description;
        this.pub_date = pub_date;
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
}
