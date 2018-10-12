package com.example.benja.myapplication.Utils.Popular_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class PopularArticleImagesFolder {
    @SerializedName("media-metadata")
    List<PopularArticleImages> mediaData;

    public PopularArticleImagesFolder(List<PopularArticleImages> mediaData) {
        this.mediaData = mediaData;
    }

    public List<PopularArticleImages> getMediaData() {
        return mediaData;
    }
}


