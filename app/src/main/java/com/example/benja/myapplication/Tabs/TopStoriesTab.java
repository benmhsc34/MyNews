package com.example.benja.myapplication.Tabs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.Api;
import com.example.benja.myapplication.Article;
import com.example.benja.myapplication.ListItem;
import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopStoriesTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    public class ArticleList {

        // ...
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.top_stories_tab, container, false);
        TextView tv = rootView.findViewById(R.id.section_label);
        final ImageView imageView = rootView.findViewById(R.id.pic);

        recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listItems = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<com.example.benja.myapplication.Article> call = api.getArticles();


        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, retrofit2.Response<Article> response) {
                Article articles = response.body();



                String[] articleNames = new String[articles.size()];

                for (int i = 0; i < articles.size(); i++){
                    articleNames[i] = articles.get(i).getTitle();
                    ListItem listItem = new ListItem(articles.get(i).getTitle(),articles.get(i).getCreated_date(), articles.get(i).getUrl(), getContext());
                    listItems.add(listItem);
                }
                adapter = new MyAdapter(listItems, getContext());

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

}


