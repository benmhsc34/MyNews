package com.example.benja.myapplication.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.ListItem;
import com.example.benja.myapplication.MyAdapter;
import com.example.benja.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PopularStoriesTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.popular_stories_tab, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listItems = new ArrayList<>();

        for (int i = 0; i <= 91; i++) {
            final ListItem listItem = new ListItem("section", "subsection", "lorem", "DATE", "http://static01.nyt.com/images/2018/10/09/briefing/100918evening-briefing-promo/100918evening-briefing-promo-thumbStandard.jpg", getContext());
         listItems.add(listItem);
        }
        adapter = new MyAdapter(listItems, getContext());

        recyclerView.setAdapter(adapter);
        return rootView;
    }
}