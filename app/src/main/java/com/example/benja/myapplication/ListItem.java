package com.example.benja.myapplication;

public class ListItem {

    String header;
    String desc;

    public ListItem(String header, String desc) {
        this.header = header;
        this.desc = desc;
    }


    public String getHeader() {
        return header;
    }

    public String getDesc() {
        return desc;
    }
}
