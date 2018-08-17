package com.muthama.nbojavadevs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muthama.nbojavadevs.R;
import com.muthama.nbojavadevs.model.GithubUsers;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("user")){
            final GithubUsers user = getIntent().getParcelableExtra("user");

            String imageUrl = user.getImageUrl();
            String imageName = user.getUsername();
            String profile = user.getProfileUrl();

            setImage(imageUrl, imageName, profile);

            Button shareButton = findViewById(R.id.share_button);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT,
                            "Check out this awesome developer @" + user.getUsername() + ", "
                                    +
                                    user.getProfileUrl());
                    startActivity(Intent.createChooser(shareIntent, "Share link using"));
                }
            });
        }
    }


    private void setImage(String imageUrl, String imageName, String profile){
        ImageView image = findViewById(R.id.detail_image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

        TextView name = findViewById(R.id.detail_name);
        name.setText(imageName);

        TextView profileUrl = findViewById(R.id.detail_profile);
        profileUrl.setText(profile);

    }
}
