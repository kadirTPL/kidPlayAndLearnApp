package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dayPlayingActivity extends AppCompatActivity {

    Button submitButton;
    Integer score=0,qnum=0;
    EditText e1;
    TextView title;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_playing);
        submitButton=(Button) findViewById(R.id.dayButton);
        e1=(EditText) findViewById(R.id.dayPeditText);
        title=(TextView) findViewById(R.id.dayPTitle);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               switch (qnum){
                   case 0:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.firstdayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                           score++;
                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.firstdayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.second));
                       break;
                   case 1:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.seconddayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                           score++;
                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.seconddayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.third));
                   break;
                   case 2:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.thirddayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                            score++;
                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.thirddayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.fourth));
                       break;
                   case 3:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.fourthdayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                           score++;

                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+ " "+getString(R.string.fourthdayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.fifth));
                       break;
                   case 4:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.fifthdayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                            score++;
                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.fifthdayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.sixth));
                       break;
                   case 5:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.sixthdayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                           score++;

                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.sixthdayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;
                       e1.setText("");
                       e1.setHint(getString(R.string.seventh));
                       break;
                   case 6:
                       if(e1.getText().toString().toUpperCase().equals(getString(R.string.seventhdayoftheWeek).toString().toUpperCase()))
                       {
                           title.setText(getString(R.string.dayPwelldone));
                           title.setBackgroundColor(getColor(R.color.green));
                           title.setTextColor(getColor(R.color.black));
                            score++;
                       }
                       else{
                           title.setText(getString(R.string.dayPwrong)+" "+getString(R.string.seventhdayoftheWeek));
                           title.setBackgroundColor(getColor(R.color.red));
                           title.setTextColor(getColor(R.color.white));
                       }
                       qnum++;

                       e1.setVisibility(View.INVISIBLE);
                       submitButton.setText(getString(R.string.clkPsee_result));
                       break;
                   case 7:
                       submitButton.setVisibility(View.INVISIBLE);
                       title.setText(getString(R.string.clkPyour_score)+score+"/7");
                        String dayScore=score.toString()+"/7";
                        Map<String,Object>dayInfo=new HashMap<>();
                        dayInfo.put("dayScore",dayScore);
                        mFirestore.collection("Users").document(UID.toString()).update(dayInfo);
                        break;
               }







            }
        });


    }







}