package com.example.benja.myapplication.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.ListItem;
import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticle;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticleList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularStoriesTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.popular_stories_tab, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listItems = new ArrayList<>();
        adapter = new MyAdapter(listItems, getContext());


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<PopularArticleList> call = api.getPopularArticles();

        recyclerView.setAdapter(adapter);
        call.enqueue(new Callback<PopularArticleList>() {
            @Override
            public void onResponse(Call<PopularArticleList> call, Response<PopularArticleList> response) {
                PopularArticleList articles = response.body();
                List<PopularArticle> theListOfArticles = articles.getArticles();


                //theListOfArticles.get(0).getMedia().get(0).getMediaData().get(0).getUrl();


                for (int i = 0; i < articles.getArticles().size(); i++) {
                    if (theListOfArticles.get(i).getMedia() == null) {
                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                "",
                                theListOfArticles.get(i).getTitle(),
                                theListOfArticles.get(i).getPublished_date(),
                                "https://static01.nyt.com/images/2018/10/11/opinion/11krugmanWeb/11krugmanWeb-thumbStandard.jpg".replace("https://", "http://"),
                                getContext());
                        listItems.add(listItem);
                    } else {
                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                "",
                                theListOfArticles.get(i).getTitle(),
                                theListOfArticles.get(i).getPublished_date(),
                                theListOfArticles.get(i).getMedia().get(0).getMediaData().get(i).getUrl(),
                                //.replace("https://", "http://")
                                getContext());
                        listItems.add(listItem);
                    }


                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<PopularArticleList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("JSON", t.getMessage());
            }
        });


        return rootView;
    }
}