package com.example.artem.Home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.artem.R;

public class ContentActivity extends AppCompatActivity {

    ImageView imageView;
    TextView mtitle;
    TextView martist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.d_1));
        }

        Intent i=getIntent();
       String title= i.getStringExtra("title");
       String image_url= i.getStringExtra("imageurl");
       String artist= i.getStringExtra("artist");

       imageView=findViewById(R.id.image_frame);
       mtitle=findViewById(R.id.my_title);
       martist=findViewById(R.id.artist_name);

        Glide.with(this).load(image_url).placeholder(R.drawable.blue).into(imageView);
        mtitle.setText(title);
        martist.setText(artist);







    }
}