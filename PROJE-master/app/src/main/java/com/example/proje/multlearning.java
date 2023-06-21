package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class multlearning extends AppCompatActivity {
Integer pageN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multlearning);
    }

    public void goToM(View view) {
        finish();
    }
    public void openP(View view) {
        if (pageN==0){
            goToM(view);
        }
        else{
            pageN--;
            switch (pageN) {
                case 0:
                    setContentView(R.layout.activity_multlearning);
                    break;
                case 1:
                    setContentView(R.layout.multlearn1);
                    break;
            }
        }
    }

    public void openN(View view) {
        if (pageN==2){
            setContentView(R.layout.multlearn2);
        }
        else{
            pageN++;
            switch (pageN) {

                case 1:
                    setContentView(R.layout.multlearn1);

                    break;
                case 2:
                    setContentView(R.layout.multlearn2);
                    break;
            }
        }
    }
}
