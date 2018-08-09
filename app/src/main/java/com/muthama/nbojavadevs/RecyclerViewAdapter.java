package com.muthama.nbojavadevs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mRepos = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImages, ArrayList<String> mNames, ArrayList<String> mDates, ArrayList<String> mRepos) {
        this.mImages = mImages;
        this.mNames = mNames;
        this.mDates = mDates;
        this.mRepos = mRepos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.rImage);

        holder.rImageName.setText(mNames.get(position));
        holder.rImageDate.setText(mDates.get(position));
        holder.rImageRepos.setText(mRepos.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name", mNames.get(position));
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView rImage;
        TextView rImageName;
        TextView rImageDate;
        TextView rImageRepos;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            rImage = itemView.findViewById(R.id.recycler_image);
            rImageName = itemView.findViewById(R.id.recycler_name);
            rImageDate = itemView.findViewById(R.id.recycler_date);
            rImageRepos = itemView.findViewById(R.id.recycler_repos);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
