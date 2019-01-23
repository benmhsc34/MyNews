package com.example.benja.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Toolbar.NotificationActivity;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.RepeatingActivity;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Notification.CATEGORY_MESSAGE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.CATEGORY_APP_MESSAGING;
import static android.content.Intent.getIntent;
import static com.example.benja.myapplication.MyNews.CHANNEL_1_ID;
import static com.example.benja.myapplication.Toolbar.NotificationActivity.MY_PREFS_NAME;

public class NotficationReceiver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManager;


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
        Boolean artsCB = prefs.getBoolean("isArtsChecked", false);
        Boolean politicsCB = prefs.getBoolean("isPoliticsChecked", false);
        Boolean entrepreneursCB = prefs.getBoolean("isEntrepreneursChecked", false);
        Boolean travelCB = prefs.getBoolean("isTravelChecked", false);
        Boolean sportsCB = prefs.getBoolean("isSportsChecked", false);
        Boolean businessCB = prefs.getBoolean("isBusinessChecked", false);

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
                @Override
                public void onResponse(Call<SearchArticleList> call, Response<SearchArticleList> response) {
                    SearchArticleList articles = response.body();
                    SearchArticleFolder theListOfArticles = articles.getResponse();
                    if (theListOfArticles.getDocs().size() != 0) {
                        if (Build.VERSION.SDK_INT <= 25) {
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                                    .setContentIntent(pendingIntent)
                                    .setSmallIcon(R.drawable.mn)
                                    .setContentTitle("My News")
                                    .setContentText("Your articles of the day are ready")
                                    .setAutoCancel(true);
                            notificationManager.notify(100, builder.build());
                        } else {

                            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, MyNews.CHANNEL_1_ID)
                                    .setSmallIcon(R.drawable.ic_notifications)
                                    .setContentTitle("Channel Title")
                                    .setContentText("Channel Text yo")
                                    .setOngoing(true);
                            notificationBuilder.build();
                            notificationManager.notify(1, notificationBuilder.build());




                            /* Create or update.
                            NotificationChannel channel = new NotificationChannel("my_channel_01",
                                    "Channel human readable title",
                                    NotificationManager.IMPORTANCE_DEFAULT);
                            //         mNotificationManager.createNotificationChannel(channel);
                            int notifyID = 1;
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            CharSequence name = ("channel_name");// The user-visible name of the channel.
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            // Create a notification and set the notification channel.
                            Notification notification = new Notification.Builder(context)
                                    .setContentTitle("New Message")
                                    .setContentText("You've received new messages.")
                                    .setSmallIcon(R.drawable.mn)
                                    .setChannelId(CHANNEL_ID)
                                    .build();*/
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchArticleList> call, Throwable t) {

                }
            });
        }


    }

    public void sendOnChannel1(View v) {

    }

    public void sendOnChannel2(View v) {

    }
}
