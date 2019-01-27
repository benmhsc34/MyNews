package com.example.benja.myapplication.Utils.Top_API;

import com.google.gson.annotations.SerializedName;

public class TopArticleImages {

    @SerializedName("url")
    private String urlImage;
    private String height;
    private String width;
    private String caption;
    private String copyright;

    public String getUrlImage() { return urlImage; }

}
