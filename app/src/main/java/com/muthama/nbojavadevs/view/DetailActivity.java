package com.muthama.nbojavadevs.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

            setImage(imageUrl, imageName);
        }
    }

    private void setImage(String imageUrl, String imageName){
        ImageView image = findViewById(R.id.detail_image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

        TextView name = findViewById(R.id.detail_name);
        name.setText(imageName);

        TextView smallName = findViewById(R.id.detail_small_name);
        smallName.setText("@" + imageName);

    }
}
