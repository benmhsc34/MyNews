package com.example.benja.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";

    @GET("home.json?api-key=5179fffa2a6545a0af9de0645194e78f")
    Call<ArticleList> getFirstArticles();



}
