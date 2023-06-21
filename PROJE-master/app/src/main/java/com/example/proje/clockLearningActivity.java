package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class clockLearningActivity extends AppCompatActivity {
    Integer pageNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_learn);
    }

    public void goToMainPage(View view) {
       finish();
    }

    public void openPreviousPage(View view) {
        if (pageNumber==0){
            goToMainPage(view);
        }
        else{
            pageNumber--;
            switch (pageNumber) {
                case 0:
                    setContentView(R.layout.activity_clock_learn);

                break;
                case 1:
                    setContentView(R.layout.activity_clock_learn1);

                break;
                case 2:
                    setContentView(R.layout.activity_clock_learn2);

                break;

            }
        }
    }

    public void openNextPage(View view) {
        if (pageNumber==3){
            Toast.makeText(this, "You are on the last page!", Toast.LENGTH_SHORT).show();
        }
        else{
            pageNumber++;
            switch (pageNumber) {

                case 1:
                    setContentView(R.layout.activity_clock_learn1);

                    break;
                case 2:
                    setContentView(R.layout.activity_clock_learn2);

                    break;
                case 3:
                    setContentView(R.layout.activity_clock_learn3);

                    break;
            }
        }
    }
}