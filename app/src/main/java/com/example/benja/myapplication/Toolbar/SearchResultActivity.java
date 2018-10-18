package com.example.benja.myapplication.Toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.ListItem;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticle;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticleList;
import com.example.benja.myapplication.Utils.Search_API.SeachArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticle;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        View rootView = View.inflate(this, R.layout.activity_search_result, recyclerView);
        recyclerView = rootView.findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        adapter = new MyAdapter(listItems, this);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<SearchArticleList> call = api.getSearchArticles();

        recyclerView.setAdapter(adapter);
        call.enqueue(new Callback<SearchArticleList>() {
            @Override
            public void onResponse(Call<SearchArticleList> call, Response<SearchArticleList> response) {
                SearchArticleList articles = response.body();
                List<SeachArticleFolder> theListOfArticles = articles.getResponse();

                for (int i = 0; i < theListOfArticles.size(); i++) {


                    ListItem listItem = new ListItem("",
                            "",
                            theListOfArticles.get(i).getDocs().get(0).getDescription(),
                            theListOfArticles.get(i).getDocs().get(0).getPub_date(),
                            "https://static01.nyt.com/images/2018/10/11/opinion/11krugmanWeb/11krugmanWeb-thumbStandard.jpg".replace("https://", "http://"),
                            theListOfArticles.get(i).getDocs().get(0).getWeb_url(),
                            SearchResultActivity.this);
                    listItems.add(listItem);

                }
            }

            @Override
            public void onFailure(Call<SearchArticleList> call, Throwable t) {

            }
        });
    }
}
