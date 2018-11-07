package com.example.benja.myapplication.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Intent.getIntent;

public class NotficationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String searchQuery = null;
        List<String> categoriesSelected = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        if(action.equals("my.action.string")){
            searchQuery = intent.getExtras().getString("searchQuery","");
            categoriesSelected = intent.getStringArrayListExtra("categoriesSelected");
        }

        Log.d("asdf","my time worked");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<SearchArticleList> call = null;

        switch (categoriesSelected.size()) {
            case 0:
                call = api.getSearchArticles(searchQuery, "", dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 1:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 2:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0) + "," + categoriesSelected.get(1), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 3:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0) + "," + categoriesSelected.get(1) + "," + categoriesSelected.get(2), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 4:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0) + "," + categoriesSelected.get(1) + "," + categoriesSelected.get(2) + "," + categoriesSelected.get(3), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 5:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0) + "," + categoriesSelected.get(1) + "," + categoriesSelected.get(2) + "," + categoriesSelected.get(3) + "," + categoriesSelected.get(4), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
            case 6:
                call = api.getSearchArticles(searchQuery, categoriesSelected.get(0) + "," + categoriesSelected.get(1) + "," + categoriesSelected.get(2) + "," + categoriesSelected.get(3) + "," + categoriesSelected.get(4) + "," + categoriesSelected.get(5), dateFormat.format(date), null, "5179fffa2a6545a0af9de0645194e78f");
                break;
        }

        call.enqueue(new Callback<SearchArticleList>() {
            @Override
            public void onResponse(Call<SearchArticleList> call, Response<SearchArticleList> response) {
                SearchArticleList articles = response.body();
                SearchArticleFolder theListOfArticles = articles.getResponse();
                if (theListOfArticles.getDocs().size() != 0){

                }
            }

            @Override
            public void onFailure(Call<SearchArticleList> call, Throwable t) {

            }
        });



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder(context).setContentTitle("Title").setContentText("Text").setSmallIcon(R.drawable.image).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notify);
    }
}
