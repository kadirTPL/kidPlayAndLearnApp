package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Locale;
import android.content.res.Configuration;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage();
    }


    private void updateLanguage() {
        String language = retrieveLanguagePreference();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        Context applicationContext = getApplicationContext();
        applicationContext.getResources().updateConfiguration(configuration, applicationContext.getResources().getDisplayMetrics());
    }

    private String retrieveLanguagePreference() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("language", "en");
    }

    public void openplayingactivity(View view) {
        Intent intForPlay=new Intent(this, playingActivity.class);
        intForPlay.putExtra("UID",UID);
        startActivity(intForPlay);
    }

    public void openlearningactivity(View view) {
        Intent intForLearn=new Intent(this, learningekrani.class);
        startActivity(intForLearn);
    }

    public void openscoreboardactivity(View view) {
        Intent intForScore=new Intent(this, ScoreboardActivity.class);
        intForScore.putExtra("UID",UID);
    startActivity(intForScore);
    }

    public void opensettingsactivity(View view) {
        Intent intForSet=new Intent(this, settingsekrani.class);
        intForSet.putExtra("UID",UID);
        startActivity(intForSet);
    }
}
