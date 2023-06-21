package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class clockPlayingActivity extends AppCompatActivity {
    int qNum = 0;
    RadioGroup radioG;
    RadioButton R2, R3, R4;
    Button subBut;
    TextView txt1, txt2,lastxt,txtwelldone;
    ImageView clockImg;
    String score,UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    int correctAns,rightAmount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_playing);
        radioG = (RadioGroup) findViewById(R.id.radioGroup);
        R2 = (RadioButton) findViewById(R.id.radioButton2);
        R3 = (RadioButton) findViewById(R.id.radioButton3);
        R4 = (RadioButton) findViewById(R.id.radioButton4);
        subBut = (Button) findViewById(R.id.button19);
        lastxt=(TextView)findViewById(R.id.lastmsg);
        txtwelldone=(TextView)findViewById(R.id.lastmsg2);
        txt1 = (TextView) findViewById(R.id.resultxt1);
        txt2 = (TextView) findViewById(R.id.resultxt2);
        clockImg=(ImageView)findViewById(R.id.clkImg);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        clockImg.setImageDrawable(getResources().getDrawable(R.drawable.eleven_thirtyfive));
        subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subBut.getText()==getResources().getString(R.string.clkPreturn_home)){
                    finish();

                }
                   else if(qNum==8){
                       score=getString(R.string.clkPyour_score)+" "+rightAmount+"/8";
                       lastxt.setText(score);
                       lastxt.setVisibility(View.VISIBLE);
                       subBut.setText(R.string.clkPreturn_home);
                       txt2.setVisibility(View.INVISIBLE);
                       txt1.setVisibility(View.INVISIBLE);
                       txtwelldone.setVisibility(View.VISIBLE);
                   }
                   else if (radioG.getCheckedRadioButtonId() == -1) {
                       Toast.makeText(clockPlayingActivity.this, "You should select an answer first", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       setCorrectAns();
                       if (radioG.getCheckedRadioButtonId() == correctAns) {
                           setResultRight();
                           rightAmount++;
                       } else {
                           setResultWrong();

                       }
                       qNum++;
                       if(qNum<8){
                       questionSetter();}
                        else{
                            clockImg.setVisibility(View.INVISIBLE);
                            radioG.setVisibility(View.INVISIBLE);
                            subBut.setText(R.string.clkPsee_result);
                            lastxt.setVisibility(View.INVISIBLE);

                            Map<String,Object> clockScoreInfo=new HashMap<>();
                            String scoreAString=rightAmount+"/8";
                            clockScoreInfo.put("clockScore",scoreAString);
                            mFirestore.collection("Users").document(UID)
                                    .update(clockScoreInfo);

                       }

                   }


            }
        });
    }
    private void setResultRight(){
        txt1.setText(R.string.clkPcorrect);
        txt1.setBackgroundColor(getColor(R.color.green));
        txt1.setTextColor(getColor(R.color.black));
        txt2.setVisibility(View.INVISIBLE);
    }
    private void setResultWrong(){
        txt1.setText(R.string.clkPwrong);
        txt1.setTextColor(getColor(R.color.white));
        txt1.setBackgroundColor(getColor(R.color.red));
        txt2.setVisibility(View.VISIBLE);
        txt2.setTextColor(getColor(R.color.white));
        txt2.setBackgroundColor(getColor(R.color.red));
        switch(qNum){
            case 0:
                txt2.setText(R.string.clkPeleventhirtyfive);
            break;
            case 1:
                txt2.setText(R.string.clkPoneoclock);
                break;
            case 2:
                txt2.setText(R.string.clkPsevenfifteen);
                break;
            case 3:
                txt2.setText(R.string.clkPsevenfortyfive);
                break;
            case 4:
                txt2.setText(R.string.clkPtwelvethirty);
                break;
            case 5:
                txt2.setText(R.string.clkPdig0407);
                break;
            case 6:
                txt2.setText(R.string.clkPdig1308);
                break;
            case 7:
                txt2.setText(R.string.clkPdig2352);
                break;






        }

    }
    private void questionSetter(){
        switch(qNum){
            case 0:

                break;
            case 1:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.one));
            R2.setText(R.string.clkPsevenfifteen);
            R4.setText(R.string.clkPoneoclock);

            R3.setText(R.string.clkPeleventhirtyfive);
                break;
            case 2:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.seven_fifteen));
                R2.setText(R.string.clkPsevenfifteen);

                R3.setText(R.string.clkPsevenfortyfive);
                R4.setText(R.string.clkPeleventhirtyfive);
                break;
            case 3:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.seven_fortyfive));
                R2.setText(R.string.clkPtwelvethirty);
                R3.setText(R.string.clkPeleventhirtyfive);

                R4.setText(R.string.clkPsevenfortyfive);
                break;
            case 4:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.twelve_thirty));
                R2.setText(R.string.clkPoneoclock);
                R3.setText(R.string.clkPtwelvethirty);

                R4.setText(R.string.clkPsevenfortyfive);
                break;
            case 5:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.d0407));
                R2.setText(R.string.clkPdig0407);
                R3.setText(R.string.clkPdig1308);

                R4.setText(R.string.clkPdig2352);
                break;
            case 6:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.d1308));
                R2.setText(R.string.clkPdig0407);
                R3.setText(R.string.clkPdig2352);

                R4.setText(R.string.clkPdig1308);
                break;
            case 7:
                clockImg.setImageDrawable(getResources().getDrawable(R.drawable.d2352));
                R2.setText(R.string.clkPdig0407);
                R3.setText(R.string.clkPdig2352);

                R4.setText(R.string.clkPdig1308);
                break;










        }

    }
    private void setCorrectAns(){
        switch(qNum){
            case 0:
                correctAns=R.id.radioButton2;
                break;
            case 1:
                correctAns=R.id.radioButton4;
                break;
            case 2:
                correctAns=R.id.radioButton2;
                break;
            case 3:
                correctAns=R.id.radioButton4;
                break;
            case 4:
                correctAns=R.id.radioButton3;
                break;
            case 5:
                correctAns=R.id.radioButton2;
                break;
            case 6:
                correctAns=R.id.radioButton4;
                break;
            case 7:
                correctAns=R.id.radioButton3;
                break;



        }




    }




}







