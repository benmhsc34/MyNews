package com.example.benja.myapplication.Tabs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.benja.myapplication.Api;
import com.example.benja.myapplication.Article;
import com.example.benja.myapplication.ListItem;
import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomStoriesTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    public static final String URL_DATA = "http://api.nytimes.com/svc/topstories/v2/home.json?api-key=5179fffa2a6545a0af9de0645194e78f";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_stories_tab, container, false);


        recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listItems = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<List<Article>> call = api.getArticles();

        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, retrofit2.Response<List<Article>> response) {
                List<Article> articles = response.body();

                String[] articleNames = new String[articles.size()];

                for (int i = 0; i < articles.size(); i++){
                    articleNames[i] = articles.get(i).getTitle();
                    ListItem listItem = new ListItem(articles.get(i).getTitle(),articles.get(i).getCreated_date(), "https://static01.nyt.com/images/2018/10/09/briefing/100918evening-briefing-promo/100918evening-briefing-promo-thumbLarge.jpg", getContext());

                    listItems.add(listItem);
                }
                adapter = new MyAdapter(listItems, getContext());

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

}