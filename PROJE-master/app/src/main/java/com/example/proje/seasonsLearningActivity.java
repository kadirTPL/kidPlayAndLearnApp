package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import android.os.Bundle;


public class seasonsLearningActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_learning);

        ImageView summer = findViewById(R.id.seasimg1);
        ImageView autumn = findViewById(R.id.seasimg2);
        ImageView winter = findViewById(R.id.seasimg3);
        ImageView spring = findViewById(R.id.seasimg4);

        Glide.with(this).asGif().load(R.drawable.summerimg).into(summer);
        Glide.with(this).asGif().load(R.drawable.autumnimg).into(autumn);
        Glide.with(this).asGif().load(R.drawable.winterimg).into(winter);
        Glide.with(this).asGif().load(R.drawable.springimg).into(spring);
    }
}
