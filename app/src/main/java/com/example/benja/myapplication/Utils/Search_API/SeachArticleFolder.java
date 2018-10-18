package com.example.benja.myapplication.Utils.Search_API;

import java.util.List;

public class SeachArticleFolder {
    List<SearchArticle> docs;

    public SeachArticleFolder(List<SearchArticle> docs) {
        this.docs = docs;
    }

    public List<SearchArticle> getDocs() {
        return docs;
    }
}
