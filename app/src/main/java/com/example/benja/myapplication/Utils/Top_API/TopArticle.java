package com.example.benja.myapplication.Utils.Top_API;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArticle {

    String section;
    @SerializedName("abstract")
    String description;
    String subsection;
    String title;
    String url;
    String byline;
    String item_type;
    String update_date;
    String created_date;
    String published_date;
    String material_type_facet;
    String kicker;
    List<TopArticleImages> multimedia;

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getItem_type() {
        return item_type;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public String getKicker() {
        return kicker;
    }

    public String getDescription() { return description; }

    public List<TopArticleImages> getMultimedia() { return multimedia; }

    public TopArticle(String section, String description, String subsection, String title, String url, String byline, String item_type, String update_date, String created_date, String published_date, String material_type_facet, String kicker, List<TopArticleImages> multimedia) {
        this.section = section;
        this.description = description;
        this.subsection = subsection;
        this.title = title;
        this.url = url;
        this.byline = byline;
        this.item_type = item_type;
        this.update_date = update_date;
        this.created_date = created_date;
        this.published_date = published_date;
        this.material_type_facet = material_type_facet;
        this.kicker = kicker;
        this.multimedia = multimedia;
    }
}

