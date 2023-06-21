package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class playingActivity extends AppCompatActivity {
String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
    }
    public void openmultgameactivity(View view) {
        Intent intForMultg=new Intent(this, MultGameActivity.class);
        intForMultg.putExtra("UID",UID);
        startActivity(intForMultg);
    }

    public void openClockPlay(View view) {
        Intent i=new Intent(this, clockPlayingActivity.class);
        i.putExtra("UID",UID);
        startActivity(i);
    }

    public void openDayPlay(View view) {
        Intent i=new Intent(this,dayPlayingActivity.class);
        i.putExtra("UID",UID);
        startActivity(i);
    }
    public void openMonthPlay(View view) {
        Intent intForMonthg=new Intent(this, monthgame.class);
        intForMonthg.putExtra("UID",UID);
        startActivity(intForMonthg);
    }

    public void openDirectionsPlay(View view) {
        Intent i =new Intent(this, directionsPlayActivity.class);
        i.putExtra("UID",UID);
        startActivity(i);
    }

    public void openWordPlay(View view) {
        Intent intForWordG=new Intent(this, wordsplaying.class);
        intForWordG.putExtra("UID",UID);
        startActivity(intForWordG);
    }
    public void openMemoryPlay(View view) {
        Intent intForMemoryG=new Intent(this, memorygame.class);
        intForMemoryG.putExtra("UID",UID);
        startActivity(intForMemoryG);
    }
    public void openrevMemoryPlay(View view) {
        Intent intForrevMemoryG=new Intent(this, reversememorygame.class);
        intForrevMemoryG.putExtra("UID",UID);
        startActivity(intForrevMemoryG);
    }

    public void openSeasonsPlay(View view) {
        Intent i=new Intent(this, seasonsPlayingActivity.class);
        i.putExtra("UID",UID);
        startActivity(i);
    }

    public void openSimilarPic(View view) {
        Intent i=new Intent(this, similarPicturesActivity.class);
        i.putExtra("UID",UID);
        startActivity(i);
    }
    public void openBallGame(View view) {
        Intent iforballg =new Intent(this, ballgame.class);
        iforballg.putExtra("UID",UID);
        startActivity(iforballg);
    }
}
