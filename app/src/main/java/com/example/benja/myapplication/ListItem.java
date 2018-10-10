package com.example.benja.myapplication;

import android.content.Context;
import android.net.Uri;

public class ListItem {

    String header;
    String desc;
    String url;
    Context context;


    public ListItem(String header, String desc, String url, Context context) {
        this.header = header;
        this.desc = desc;
        this.url = url;
        this.context = context;
    }

    public String getHeader() {

        return header;
    }

    public String getDesc() {

        return desc;
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }
}
