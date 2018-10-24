package com.example.benja.myapplication.Toolbar;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.ListItem;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticle;
import com.example.benja.myapplication.Utils.Popular_API.PopularArticleList;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticle;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
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

        String searchQuery = null;
        String theBeginDateString = null;
        String theEndDateString = null;
        ArrayList<String> categoriesSelected = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchQuery = bundle.getString("searchQuery");
            theBeginDateString = bundle.getString("theBeginDateString");
            theEndDateString = bundle.getString("theEndDateString");
            categoriesSelected = bundle.getStringArrayList("categoriesSelected");
        }


        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
        listItems = new ArrayList<>();
        adapter = new MyAdapter(listItems, SearchResultActivity.this);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<SearchArticleList> call = api.getSearchArticles(searchQuery, categoriesSelected.get(0), theBeginDateString, theEndDateString, "5179fffa2a6545a0af9de0645194e78f");


        recyclerView.setAdapter(adapter);
        call.enqueue(new Callback<SearchArticleList>() {
            @Override
            public void onResponse(Call<SearchArticleList> call, retrofit2.Response<SearchArticleList> response) {
                SearchArticleList articles = response.body();
                SearchArticleFolder theListOfArticles = articles.getResponse();

                if (theListOfArticles != null) {

                    for (int i = 0; i < theListOfArticles.getDocs().size(); i++) {
                        if (theListOfArticles.getDocs().get(i).getMultimedia().size() != 0) {
                            ListItem listItem = new ListItem("",
                                    "",
                                    theListOfArticles.getDocs().get(i).getSnippet(),
                                    theListOfArticles.getDocs().get(i).getPub_date(),
                                    "http://www.nytimes.com/" + theListOfArticles.getDocs().get(i).getMultimedia().get(1).getUrl(),
                                    theListOfArticles.getDocs().get(i).getWeb_url(),
                                    SearchResultActivity.this);
                            listItems.add(listItem);
                        }
                        else {
                            ListItem listItem = new ListItem("",
                                    "",
                                    theListOfArticles.getDocs().get(i).getSnippet(),
                                    theListOfArticles.getDocs().get(i).getPub_date(),
                                    "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/44686792_1020357278142901_5098647331683696640_n.jpg?_nc_cat=108&_nc_ht=scontent-cdg2-1.xx&oh=dc5de8b11cdc369b0240a420f09e2d2a&oe=5C5547E8".replace("https://", "http://"),
                                    theListOfArticles.getDocs().get(i).getWeb_url(),
                                    SearchResultActivity.this);
                            listItems.add(listItem);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchArticleList> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("JSON", t.getMessage());
            }
        });
    }
}
