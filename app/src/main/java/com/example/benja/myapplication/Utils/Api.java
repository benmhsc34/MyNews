package com.example.benja.myapplication.Utils;

import com.example.benja.myapplication.Utils.Popular_API.PopularArticleList;
import com.example.benja.myapplication.Utils.Top_API.TopArticleList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://api.nytimes.com/svc/";

    @GET("topstories/v2/home.json?api-key=5179fffa2a6545a0af9de0645194e78f")
    Call<TopArticleList> getTopArticles();

    @GET("mostpopular/v2/mostshared/all-sections/7.json?api-key=5179fffa2a6545a0af9de0645194e78f")
    Call<PopularArticleList> getPopularArticles();

}
