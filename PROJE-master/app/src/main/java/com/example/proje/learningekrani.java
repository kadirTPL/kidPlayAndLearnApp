package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class learningekrani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningekrani);
    }

    public void openClockLearn(View view) {
        Intent iforclocklearn=new Intent(this, clockLearningActivity.class);
        startActivity(iforclocklearn);
    }
    public void openMultLearn(View view) {
        Intent iformultlearn=new Intent(this, multlearning.class);
        startActivity(iformultlearn);
    }
    public void openMonthLearn(View view) {
        Intent iformonthlearn=new Intent(this, monthlearn.class);
        startActivity(iformonthlearn);
    }
    public void openWordsLearn(View view) {
        Intent iforwordslearn=new Intent(this, wordlearning.class);
        startActivity(iforwordslearn);
    }
    public void openDayLearn(View view) {
        Intent ifordaylearn=new Intent(this, dayLearningActivity.class);
        startActivity(ifordaylearn);
    }

    public void openDirectionsLearn(View view) {
        Intent ifordirectlearn=new Intent(this, directionsLearnActivity.class);
        startActivity(ifordirectlearn);
    }

    public void openSeasonsLearn(View view) {
        Intent iforseaslearn=new Intent(this, seasonsLearningActivity.class);
        startActivity(iforseaslearn);
    }
}