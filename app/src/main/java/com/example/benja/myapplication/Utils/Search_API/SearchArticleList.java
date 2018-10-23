package com.example.benja.myapplication.Utils.Search_API;

import java.util.List;
import retrofit2.Call;

public class SearchArticleList {
    private SearchArticleFolder response;

    public SearchArticleList(SearchArticleFolder response) {
        this.response = response;
    }

    public SearchArticleFolder getResponse() {
        return response;
    }
}
