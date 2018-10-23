package com.example.benja.myapplication.Toolbar;

import android.os.Parcelable;
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

        //Call<SearchArticleList> call = api.getSearchArticles(searchQuery, categoriesSelected.get(0), theBeginDateString, theEndDateString, "5179fffa2a6545a0af9de0645194e78f");
        Call<SearchArticleList> call = api.getSearchArticles("search query", "Sports, Business", "10122018", "10222018", "5179fffa2a6545a0af9de0645194e78f");

        recyclerView.setAdapter(adapter);
        call.enqueue(new Callback<SearchArticleList>() {
            @Override
            public void onResponse(Call<SearchArticleList> call, Response<SearchArticleList> response) {
                SearchArticleList articles = response.body();
                SearchArticleFolder theListOfArticles = articles.getResponse();

                if (theListOfArticles != null) {
                    for (int i = 0; i < theListOfArticles.getDocs().size(); i++) {
                        ListItem listItem = new ListItem("",
                                "",
                                theListOfArticles.getDocs().get(i).getDescription(),
                                theListOfArticles.getDocs().get(i).getPub_date(),
                                "data:image/png;base64,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".replace("https://", "http://"),
                                theListOfArticles.getDocs().get(i).getWeb_url(),
                                SearchResultActivity.this);
                        listItems.add(listItem);

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
