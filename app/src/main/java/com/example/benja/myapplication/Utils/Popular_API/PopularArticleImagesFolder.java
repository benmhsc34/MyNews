package com.example.benja.myapplication.Utils.Popular_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularArticleImagesFolder {
    @SerializedName("media-metadata")
    List<PopularArticleImages> mediaData;
    String subtype;

    public PopularArticleImagesFolder(List<PopularArticleImages> mediaData, String subtype) {
        this.mediaData = mediaData;
        this.subtype = subtype;
    }

    public List<PopularArticleImages> getMediaData() {
        return mediaData;
    }

    public String getSubtype() {
        return subtype;
    }
}


