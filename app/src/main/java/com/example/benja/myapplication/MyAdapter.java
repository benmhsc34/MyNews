package com.example.benja.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.myapplication.Toolbar.SearchActivity;
import com.example.benja.myapplication.Utils.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);

        viewHolder.textViewSection.setText(listItem.getSection());
        viewHolder.textViewSubsection.setText(listItem.getSubsection());
        if (listItem.getSubsection().equals("")) {
            viewHolder.specificTextView.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.specificTextView.setVisibility(View.VISIBLE);
        }
        viewHolder.textViewTitle.setText(listItem.getDesc());
        viewHolder.textViewDate.setText(listItem.getDate());
        Picasso.get().load(listItem.getUrlImage()).into(viewHolder.imageViewPic);

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, WebviewActivity.class);
                myIntent.putExtra("websiteUrl", listItem.getUrlWebsite());
                context.startActivity(myIntent);
                //    viewHolder.relativeLayout.setBackgroundColor(R.color.colorPrimaryDark);
            }
        });
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
        final public TextView specificTextView;
        public RelativeLayout relativeLayout;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewSection = itemView.findViewById(R.id.section);
            textViewSubsection = itemView.findViewById(R.id.subsection);
            textViewTitle = itemView.findViewById(R.id.desc);
            textViewDate = itemView.findViewById(R.id.dateTextView);
            imageViewPic = itemView.findViewById(R.id.pic);
            specificTextView = itemView.findViewById(R.id.specific);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

            ButterKnife.bind(this, itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) ;
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
