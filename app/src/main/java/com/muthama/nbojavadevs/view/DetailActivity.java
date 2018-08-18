package com.muthama.nbojavadevs.view;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.muthama.nbojavadevs.R;
import com.muthama.nbojavadevs.model.GithubUsers;

public class DetailActivity extends AppCompatActivity {

    String gitUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  Display the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // This method handles the swivel/drop of the Collapsing Toolbar.
        // This shows/hides the toolbar title on scroll.
        initCollapsingToolbar();

        getIncomingIntent();
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Java User: ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){

                // Adding the Github Username on the CollapsingBar Layout
                GithubUsers user = getIntent().getParcelableExtra("user");
                if (user != null){
                    gitUserName = user.getUsername();
                }

                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle("Java User: " + gitUserName);
                    isShow = true;
                } else if (isShow){
                    collapsingToolbarLayout.setTitle("Java User: ");
                    isShow = false;
                }
            }
        });
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
        } else{
            // In case there is no data in the Intent.
            Toast.makeText(this, "No API Data!", Toast.LENGTH_SHORT).show();
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
