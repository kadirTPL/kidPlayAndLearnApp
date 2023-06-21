package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.example.proje.ballview;
public class ballgame extends AppCompatActivity {

    private ballview ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ballgame);

        ball = new ballview(this);

        ViewGroup container = findViewById(R.id.container);
        container.addView(ball);
    }
}