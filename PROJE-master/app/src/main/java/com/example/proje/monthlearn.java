package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class monthlearn extends AppCompatActivity {
    Integer monthpage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlearn);
    }

    public void monthMain(View view) {
        finish();
    }
    public void monthPrevious(View view) {
        if (monthpage==0){
            monthMain(view);
        }
        else{
            monthpage--;
            switch (monthpage) {
                case 0:
                    setContentView(R.layout.activity_monthlearn);
                    break;
                case 1:
                    setContentView(R.layout.monthlearn1);
                    break;
            }
        }
    }

    public void monthForward(View view) {
        if (monthpage==2){
            setContentView(R.layout.monthlearn2);
        }
        else{
            monthpage++;
            switch (monthpage) {

                case 1:
                    setContentView(R.layout.monthlearn1);

                    break;
                case 2:
                    setContentView(R.layout.monthlearn2);
                    break;
            }
        }
    }
}
