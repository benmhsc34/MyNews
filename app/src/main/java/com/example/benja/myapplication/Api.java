package com.example.benja.myapplication;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";

    @GET("home.json?api-key=5179fffa2a6545a0af9de0645194e78f")
    Call<List<Hero>> getHeroes();

    class HeroList {
        @SerializedName("data")
        private List<Hero> mHeroes;

    }



}
