package com.example.benja.myapplication.Utils;

public class PopularArticleImages {
    String url;
    int height;
    int weight;

    public PopularArticleImages(String url, int height, int weight) {
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
