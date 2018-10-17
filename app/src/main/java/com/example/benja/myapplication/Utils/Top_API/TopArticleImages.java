package com.example.benja.myapplication.Utils.Top_API;

import com.google.gson.annotations.SerializedName;

public class TopArticleImages {

    @SerializedName("imageUrl") String urlImage;
    String height;
    String width;
    String caption;
    String copyright;

    public TopArticleImages(String urlImage, String height, String width, String caption, String copyright) {
        this.urlImage = urlImage;
        this.height = height;
        this.width = width;
        this.caption = caption;
        this.copyright = copyright;
    }

    public String getUrlImage() { return urlImage; }

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
