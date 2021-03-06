package com.example.benja.myapplication.Tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.Custom_API.CustomArticle;
import com.example.benja.myapplication.Utils.Custom_API.CustomArticleList;
import com.example.benja.myapplication.Utils.ListItem;
import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomStoriesTab extends Fragment {


    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.top_stories_tab, container, false);
        TextView tv = rootView.findViewById(R.id.section_label);


        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listItems = new ArrayList<>();
        adapter = new MyAdapter(listItems, getContext());


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<CustomArticleList> call = api.getCustomArticles();

        recyclerView.setAdapter(adapter);
        call.enqueue(new Callback<CustomArticleList>() {
            @Override
            public void onResponse(@NonNull Call<CustomArticleList> call, @NonNull Response<CustomArticleList> response) {
                CustomArticleList articles = response.body();
                List<CustomArticle> theListOfArticles = Objects.requireNonNull(articles).getArticles();


                //theListOfArticles.get(0).getMedia().get(0).getMediaData().get(0).getUrl();


                for (int i = 0; i < articles.getArticles().size(); i++) {

                    @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String inputDateStr = theListOfArticles.get(i).getPublished_date();
                    Date date = null;
                    try {
                        date = inputFormat.parse(inputDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String outputDateStr = outputFormat.format(date);

                    if (theListOfArticles.get(i).getMedia() == null) {
                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                "",
                                theListOfArticles.get(i).getTitle(),
                                outputDateStr,
                                "https://i.postimg.cc/tTTvdhVF/mn.png".replace("https://", "http://"),
                                theListOfArticles.get(i).getUrl(),
                                getContext());
                        listItems.add(listItem);
                    } else {
                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                "",
                                theListOfArticles.get(i).getTitle(),
                                outputDateStr,
                                theListOfArticles.get(i).getMedia().get(0).getMediaData().get(0).getUrl().replace("https://", "http://"),
                                theListOfArticles.get(i).getUrl(),
                                getContext());
                        listItems.add(listItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(@NonNull Call<CustomArticleList> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("JSON", t.getMessage());
            }
        });

        return rootView;
    }
}
