package com.example.benja.myapplication;

import android.content.Context;
import android.net.Uri;

public class ListItem {

    String section;
    String subsection;
    String desc;
    String date;
    String url;
    Context context;

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public ListItem(String section, String subsection, String desc, String date, String url, Context context) {

        this.section = section;
        this.subsection = subsection;
        this.desc = desc;
        this.date = date;
        this.url = url;
        this.context = context;
    }
}
