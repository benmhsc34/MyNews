package com.example.benja.myapplication.Utils.Search_API;

import java.util.List;

public class SearchArticleList {
    List<SeachArticleFolder> response;

    public SearchArticleList(List<SeachArticleFolder> response) {
        this.response = response;
    }

    public List<SeachArticleFolder> getResponse() {
        return response;
    }
}
