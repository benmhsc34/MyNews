package com.example.benja.myapplication.Utils.Search_API;

import java.util.List;

public class SearchArticleFolder {
    List<SearchArticle> docs;

    public SearchArticleFolder(List<SearchArticle> docs) {
        this.docs = docs;
    }

    public List<SearchArticle> getDocs() {
        return docs;
    }
}
