package com.example.benja.myapplication.Utils.Custom_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"ALL", "CanBeFinal"})
public class CustomArticleImagesFolder {

    @SuppressWarnings("CanBeFinal")
    @SerializedName("media-metadata")
    private List<CustomArticleImages> mediaData;
    private String subtype;

    public CustomArticleImagesFolder(List<CustomArticleImages> mediaData, String subtype) {
        this.mediaData = mediaData;
        this.subtype = subtype;
    }

    public List<CustomArticleImages> getMediaData() {
        return mediaData;
    }

    public String getSubtype() {
        return subtype;
    }
}
