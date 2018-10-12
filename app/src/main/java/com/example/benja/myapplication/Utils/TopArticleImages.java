package com.example.benja.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

public class TopArticleImages {

    @SerializedName("imageUrl") String url;
    String height;
    String width;
    String caption;
    String copyright;

    public TopArticleImages(String url, String height, String width, String caption, String copyright) {
        this.url = url;
        this.height = height;
        this.width = width;
        this.caption = caption;
        this.copyright = copyright;
    }

    public String getUrl() {
        return url;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }
}
