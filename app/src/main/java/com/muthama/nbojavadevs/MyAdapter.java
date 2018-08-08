package com.muthama.nbojavadevs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.LineNumberInputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewUsername.setText(ListItem.getUsername());
        holder.textViewJoinDate.setText(ListItem.getJoinDate());
        holder.textViewRepoNumber.setText(ListItem.getRepoNumber());

        Picasso.get()
                .load(listItem.getImageUrl())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewUsername;
        public TextView textViewJoinDate;
        public TextView textViewRepoNumber;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.username);
            textViewJoinDate = itemView.findViewById(R.id.join_date);
            textViewRepoNumber = itemView.findViewById(R.id.repo_number);
            mImageView = itemView.findViewById(R.id.homePageImageView);

        }
    }

}
