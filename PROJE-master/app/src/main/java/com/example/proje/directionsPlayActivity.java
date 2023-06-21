package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class directionsPlayActivity extends AppCompatActivity {

    Button leftBtn,rightBtn;
    TextView txtresult,txtquest;
    ImageView img;
    Integer qnum=0,score=0;
    String UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_play);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
    leftBtn=(Button) findViewById(R.id.directcPbut2);
    rightBtn=(Button) findViewById(R.id.directcPbut1);
    txtresult=(TextView)findViewById(R.id.directcPtxt2);
    txtquest=(TextView)findViewById(R.id.directcPtxt1);
    img=(ImageView)findViewById(R.id.directcPimg);

    leftBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setQuestion();
            txtresult.setVisibility(View.VISIBLE);
            switch (qnum){
                case 0:
                    setCorrect();
                    score++;
                    break;
                case 1:
                    setCorrect();
                    score++;
                    break;
                case 2:
                    setCorrect();
                    score++;
                    break;
                case 3:
                    setWrong();
                    break;
                case 4:
                    setCorrect();
                    score++;
                    break;
                case 5:
                    setWrong();
                    break;
                case 6:
                    setWrong();
                    break;
                case 7:
                    setWrong();
                    txtquest.setText(getString(R.string.directionsPlastmsg)+" "+score.toString()+"/8");
                    String directScore=score.toString()+"/8";
                    Map<String,Object>directInfo=new HashMap<>();
                    directInfo.put("directionsScore",directScore);
                    mFirestore.collection("Users").document(UID).update(directInfo);
                    break;
            }
            qnum++;





        }
    });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestion();
                txtresult.setVisibility(View.VISIBLE);
                switch (qnum){
                    case 0:
                        setWrong();
                        break;
                    case 1:
                        setWrong();
                        break;
                    case 2:
                        setWrong();
                        break;
                    case 3:
                        setCorrect();
                        score++;
                        break;
                    case 4:
                        setWrong();
                        break;
                    case 5:
                        setCorrect();
                        score++;
                        break;
                    case 6:
                        setCorrect();
                        score++;
                        break;
                    case 7:
                        setCorrect();
                        score++;
                        txtquest.setText(getString(R.string.directionsPlastmsg)+" "+score.toString()+"/8");
                        String directScore=score.toString()+"/8";
                        Map<String,Object>directInfo=new HashMap<>();
                        directInfo.put("directionsScore",directScore);
                        mFirestore.collection("Users").document(UID).update(directInfo);
                        break;






                }
                qnum++;




            }
        });








    }

    private void setCorrect(){
        txtresult.setTextColor(getColor(R.color.black));
        txtresult.setBackgroundColor(getColor(R.color.green));
        txtresult.setText(getString(R.string.directionsPCorrect));
    }
    private void setWrong(){
        txtresult.setBackgroundColor(getColor(R.color.red));
        txtresult.setTextColor(getColor(R.color.white));
        switch (qnum){
            case 0:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPIn));
                break;
            case 1:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPLeft));
                break;
            case 2:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPBetween));
                break;
            case 3:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPRight));
                break;
            case 4:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPFront));
                break;
            case 5:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPUnder));
                break;
            case 6:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPOn));
                break;
            case 7:
                txtresult.setText(getString(R.string.directionsPWrong)+getString(R.string.directionsPBehind));
                break;
        }



    }




    private void setQuestion(){
        // 0l,1l,2l,3r,4l,5r,6r,7r
        switch(qnum){

            case 0:
                txtquest.setText(getString(R.string.directionsPquestion3));
                img.setImageDrawable(getResources().getDrawable(R.drawable.car_onthe_left));
                leftBtn.setText(getResources().getString(R.string.directionsPLeft));
                rightBtn.setText(getResources().getString(R.string.directionsPRight));
            break;
            case 1:
                txtquest.setText(R.string.directionsPquestion2);
                img.setImageDrawable(getResources().getDrawable(R.drawable.between));
                leftBtn.setText(getResources().getString(R.string.directionsPBetween));
                rightBtn.setText(getResources().getString(R.string.directionsPIn));
                break;
            case 2:
                txtquest.setText(getString(R.string.directionsPquestion3));
                img.setImageDrawable(getResources().getDrawable(R.drawable.car_onthe_right));
                leftBtn.setText(getResources().getString(R.string.directionsPLeft));
                rightBtn.setText(getResources().getString(R.string.directionsPRight));
                break;
            case 3:
                img.setImageDrawable(getResources().getDrawable(R.drawable.in_front_of));
                txtquest.setText(getString(R.string.directionsPquestion1));
                leftBtn.setText(getResources().getString(R.string.directionsPFront));
                rightBtn.setText(getResources().getString(R.string.directionsPBehind));
                break;
            case 4:
                txtquest.setText(R.string.directionsPquestion2);
                img.setImageDrawable(getResources().getDrawable(R.drawable.under));
                leftBtn.setText(getResources().getString(R.string.directionsPOn));
                rightBtn.setText(getResources().getString(R.string.directionsPUnder));
                break;
            case 5:
                img.setImageDrawable(getResources().getDrawable(R.drawable.on));
                txtquest.setText(R.string.directionsPquestion2);
                leftBtn.setText(getResources().getString(R.string.directionsPUnder));
                rightBtn.setText(getResources().getString(R.string.directionsPOn));
                break;
            case 6:
                img.setImageDrawable(getResources().getDrawable(R.drawable.behind));
                txtquest.setText(getString(R.string.directionsPquestion1));
                leftBtn.setText(getResources().getString(R.string.directionsPFront));
                rightBtn.setText(getResources().getString(R.string.directionsPBehind));
                break;
            case 7:
                rightBtn.setVisibility(View.INVISIBLE);
                leftBtn.setVisibility(View.INVISIBLE);
                img.setVisibility(View.INVISIBLE);
                break;
        }







    }




}