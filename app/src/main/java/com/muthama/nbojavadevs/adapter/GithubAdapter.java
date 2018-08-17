package com.muthama.nbojavadevs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muthama.nbojavadevs.view.DetailActivity;
import com.muthama.nbojavadevs.R;
import com.muthama.nbojavadevs.model.GithubUsers;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {

    private ArrayList<GithubUsers> users;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView rImage;
        TextView rImageName;
        TextView rImageRepos;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            rImage = itemView.findViewById(R.id.recycler_image);
            rImageName = itemView.findViewById(R.id.recycler_name);
            rImageRepos = itemView.findViewById(R.id.recycler_repos);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }

    public GithubAdapter(ArrayList<GithubUsers> users, Context mContext) {
        this.users = users;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public GithubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        GithubAdapter.ViewHolder holder = new GithubAdapter.ViewHolder(view);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final GithubUsers user = users.get(position);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("user", user);
                mContext.startActivity(intent);

            }
        });
        Glide.with(mContext)
                .asBitmap()
                .load(user.getImageUrl())
                .into(holder.rImage);

        holder.rImageName.setText(user.getUsername());
        holder.rImageRepos.setText(user.getProfileUrl());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
