package com.example.benja.myapplication.Utils.Popular_API;

import com.example.benja.myapplication.Utils.Custom_API.CustomArticleImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularArticleImagesFolder {
    @SerializedName("media-metadata")
    private List<CustomArticleImages> mediaData;
    private String subtype;

    public List<CustomArticleImages> getMediaData() {
        return mediaData;
    }

}


