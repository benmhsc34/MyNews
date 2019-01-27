package com.example.benja.myapplication.Toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.ListItem;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        final TextView noResultsTV = findViewById(R.id.noResultsTV);


        String searchQuery = "";
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

        DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        String inputDateStr = theBeginDateString;
        String endInputDateStr = theEndDateString;
        Date date = null;
        Date endDate = null;
        String outputDateStr = null;
        String endOutputDateStr = null;

        if (inputDateStr != null) {
            try {
                date = inputFormat.parse(inputDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            outputDateStr = outputFormat.format(date);

        }
        if (endInputDateStr != null) {
            try {
                endDate = inputFormat.parse(endInputDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            endOutputDateStr = outputFormat.format(endDate);
        }


        if (searchQuery.equals("")) {
            noResultsTV.setText("Please enter a word in the search bar");
        } else {

            RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
            listItems = new ArrayList<>();
            adapter = new MyAdapter(listItems, SearchResultActivity.this);


            Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

            Api api = retrofit.create(Api.class);

            Call<SearchArticleList> call;


            call = api.getSearchArticles(searchQuery, categoriesSelected.toString().replace("[", "").replace("]", ""), outputDateStr, endOutputDateStr, "q2hYuSZfmtEyizi8LXiL0CGNh27adQoi");
            Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();

            recyclerView.setAdapter(adapter);
            if (call != null) {
                call.enqueue(new Callback<SearchArticleList>() {
                    @Override
                    public void onResponse(Call<SearchArticleList> call, Response<SearchArticleList> response) {
                        SearchArticleList articles = response.body();
                        SearchArticleFolder theListOfArticles = articles.getResponse();

                        if (theListOfArticles != null) {

                            for (int i = 0; i < theListOfArticles.getDocs().size(); i++) {

                                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
                                String inputDateStr = theListOfArticles.getDocs().get(i).getPub_date();
                                if (inputDateStr == null) {
                                    inputDateStr = "2018-10-25T21:09:24";
                                }

                                Date date = null;
                                try {
                                    date = inputFormat.parse(inputDateStr);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String outputDateStr = outputFormat.format(date);

                                if (theListOfArticles.getDocs().get(i).getMultimedia().size() != 0) {
                                    ListItem listItem = new ListItem("",
                                            "",
                                            theListOfArticles.getDocs().get(i).getSnippet(),
                                            outputDateStr,
                                            "http://www.nytimes.com/" + theListOfArticles.getDocs().get(i).getMultimedia().get(0).getUrl(),
                                            theListOfArticles.getDocs().get(i).getWeb_url(),
                                            SearchResultActivity.this);
                                    noResultsTV.setText("No articles matching your search. Try being less specific");

                                    listItems.add(listItem);
                                } else {
                                    ListItem listItem = new ListItem("",
                                            "",
                                            theListOfArticles.getDocs().get(i).getSnippet(),
                                            outputDateStr,
                                            "https://photos.google.com/search/_tra_/photo/AF1QipNoS1BYk6YZcqmJOOlUNSa_jq_Xj09ztpaKDgYz?hl=fr".replace("https://", "http://"),
                                            theListOfArticles.getDocs().get(i).getWeb_url(),
                                            SearchResultActivity.this);
                                    listItems.add(listItem);

                                }
                            }
                        }
                            noResultsTV.setText("No articles matching your search. Try being less specific");

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

}
