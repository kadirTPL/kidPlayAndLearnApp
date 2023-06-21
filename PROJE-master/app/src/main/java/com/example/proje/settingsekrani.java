package com.example.proje;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import android.content.SharedPreferences;

public class settingsekrani extends AppCompatActivity {

    private Button tr;
    private Button en;
    private Button login;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsekrani);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        tr = findViewById(R.id.turkishbutton);
        en = findViewById(R.id.englishbutton);
        login = findViewById(R.id.settingsloginbutton);


        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("tr");
                recreate();
                openMainActivity();
            }
        });

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
                recreate();
                openMainActivity();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("UID",UID);
        startActivity(intent);
    }

    private void openlogin() {
        Intent intforlogin = new Intent(this, loginActivity.class);
        startActivity(intforlogin);
    }
}