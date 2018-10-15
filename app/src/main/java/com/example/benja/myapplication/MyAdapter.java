package com.example.benja.myapplication;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benja.myapplication.Utils.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;



    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItem listItem = listItems.get(i);

        viewHolder.textViewSection.setText(listItem.getSection());
        viewHolder.textViewSubsection.setText(listItem.getSubsection());
        viewHolder.textViewTitle.setText(listItem.getDesc());
        viewHolder.textViewDate.setText(listItem.getDate());
        Picasso.with(context).load(listItem.getUrl()).into(viewHolder.imageViewPic);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewSection;
        public TextView textViewSubsection;
        public TextView textViewTitle;
        public TextView textViewDate;
        public ImageView imageViewPic;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSection = itemView.findViewById(R.id.section);
            textViewSubsection = itemView.findViewById(R.id.subsection);
            textViewTitle = itemView.findViewById(R.id.desc);
            textViewDate = itemView.findViewById(R.id.dateTextView);
            imageViewPic = itemView.findViewById(R.id.pic);


            ButterKnife.bind(this, itemView);

        }
    }

}
