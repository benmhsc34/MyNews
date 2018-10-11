package com.example.benja.myapplication;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

       viewHolder.textViewHead.append(listItem.getHeader());
       viewHolder.textViewDesc.setText(listItem.getDesc());
        Picasso.with(context).load("https://images.sudouest.fr/2018/07/15/5b4b912a66a4bd733eb04b75/widescreen/1000x500/le-port-de-la-rochelle-envahi-de-plongeurs-improvises.jpg").into(viewHolder.profileImageView);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public TextView textViewDesc;
        @BindView(R.id.pic)
        ImageView profileImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHead = itemView.findViewById(R.id.head);
            textViewDesc = itemView.findViewById(R.id.desc);
            profileImageView = itemView.findViewById(R.id.pic);
            ButterKnife.bind(this, itemView);

        }
    }

}
