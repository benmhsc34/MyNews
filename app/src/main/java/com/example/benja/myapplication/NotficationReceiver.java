package com.example.benja.myapplication;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.benja.myapplication.Toolbar.NotificationActivity.MY_PREFS_NAME;

public class NotficationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("asdf", "my time worked");
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        //   String action = intent.getAction();
        ArrayList<String> categoriesSelected = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String searchQuery = prefs.getString("editTextNotification", "");
        boolean artsCB = prefs.getBoolean("isArtsChecked", false);
        boolean politicsCB = prefs.getBoolean("isPoliticsChecked", false);
        boolean entrepreneursCB = prefs.getBoolean("isEntrepreneursChecked", false);
        boolean travelCB = prefs.getBoolean("isTravelChecked", false);
        boolean sportsCB = prefs.getBoolean("isSportsChecked", false);
        boolean businessCB = prefs.getBoolean("isBusinessChecked", false);

        if (artsCB) {
            categoriesSelected.add("arts");
        }
        if (politicsCB) {
            categoriesSelected.add("politics");
        }
        if (entrepreneursCB) {
            categoriesSelected.add("entrepreneurs");
        }
        if (travelCB) {
            categoriesSelected.add("travel");
        }
        if (sportsCB) {
            categoriesSelected.add("sports");
        }
        if (businessCB) {
            categoriesSelected.add("business");
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<SearchArticleList> call;

        call = api.getSearchArticles(searchQuery, categoriesSelected.toString().replace("[", "").replace("]", ""), dateFormat.format(date), null, "q2hYuSZfmtEyizi8LXiL0CGNh27adQoi");


        if (call != null) {
            call.enqueue(new Callback<SearchArticleList>() {
                @SuppressWarnings("deprecation")
                @Override
                public void onResponse(@NonNull Call<SearchArticleList> call, @NonNull Response<SearchArticleList> response) {
                    SearchArticleList articles = response.body();
                    SearchArticleFolder theListOfArticles = Objects.requireNonNull(articles).getResponse();
                    if (theListOfArticles.getDocs().size() != 0) {
                        if (Build.VERSION.SDK_INT <= 25) {
                            @SuppressLint("IconColors") NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                                    .setContentIntent(pendingIntent)
                                    .setSmallIcon(R.drawable.mn)
                                    .setContentTitle("My News")
                                    .setContentText("Your articles of the day are ready")
                                    .setAutoCancel(true);
                            Objects.requireNonNull(notificationManager).notify(100, builder.build());
                        } else {

                            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, MyNews.CHANNEL_1_ID)
                                    .setSmallIcon(R.drawable.ic_notifications)
                                    .setContentTitle("My News")
                                    .setContentText("Your articles of the day are ready")
                                    .setOngoing(true);
                            notificationBuilder.build();
                            Objects.requireNonNull(notificationManager).notify(1, notificationBuilder.build());

                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SearchArticleList> call, @NonNull Throwable t) {

                }
            });
        }


    }

}
