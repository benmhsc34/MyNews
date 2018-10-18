package com.example.benja.myapplication.Utils.Custom_API;

public class CustomArticleImages {
    private String url;
    private int height;
    private int weight;

    public CustomArticleImages(String url, int height, int weight) {
        this.url = url;
        this.height = height;
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
