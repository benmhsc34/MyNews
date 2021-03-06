package com.example.benja.myapplication.Tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.Top_API.TopArticle;
import com.example.benja.myapplication.Utils.Top_API.TopArticleList;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopStoriesTab extends Fragment {

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

        Call<TopArticleList> call = api.getTopArticles();

        recyclerView.setAdapter(adapter);

        call.enqueue(new Callback<TopArticleList>() {
            @Override
            public void onResponse(@NonNull Call<TopArticleList> call, @NonNull retrofit2.Response<TopArticleList> response) {
                TopArticleList articles = response.body();
                final List<TopArticle> theListOfArticles = Objects.requireNonNull(articles).getArticles();


                for (int i = 0; i < articles.getArticles().size(); i++) {

                    @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String inputDateStr = theListOfArticles.get(i).getPublished_date();
                    Date date = null;
                    try {
                        date = inputFormat.parse(inputDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String outputDateStr = outputFormat.format(date);

                    int limit = theListOfArticles.get(i).getMultimedia().size();
                    if (limit != 0) {

                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                theListOfArticles.get(i).getSubsection(),
                                theListOfArticles.get(i).getTitle(),
                                outputDateStr,
                                theListOfArticles.get(i).getMultimedia().get(0).getUrlImage().replace("https://", "http://"),
                                theListOfArticles.get(i).getUrl(),
                                getContext());
                        listItems.add(listItem);

                    } else {
                        ListItem listItem = new ListItem(theListOfArticles.get(i).getSection(),
                                theListOfArticles.get(i).getSubsection(),
                                theListOfArticles.get(i).getTitle(),
                                outputDateStr,
                                "http://i.postimg.cc/tTTvdhVF/mn.png",
                                theListOfArticles.get(i).getUrl(),
                                getContext());
                        listItems.add(listItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<TopArticleList> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("JSON", t.getMessage());
            }
        });
        return rootView;
    }
}


