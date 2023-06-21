package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class similarPicturesActivity extends AppCompatActivity {

    ImageButton img1,img2,img3;
    ImageView queImg;
    TextView resTxt,queTxt;
    Integer score=0,qnum=1;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        img1=(ImageButton) findViewById(R.id.simBut1);
    img2=(ImageButton) findViewById(R.id.simBut2);
    img3=(ImageButton) findViewById(R.id.simBut3);
    queImg=(ImageView) findViewById(R.id.simQue);
    queTxt=(TextView) findViewById(R.id.simQueTxt);
    resTxt=(TextView) findViewById(R.id.simResTxt);
    resTxt.setVisibility(View.INVISIBLE);

    img1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setQuest();
            resTxt.setVisibility(View.VISIBLE);
            switch (qnum){
                case 1:
                    setCorrect();
                    break;
                case 2:
                    setWrong();
                    break;
                case 3:
                    setWrong();
                    break;
                case 4:
                    setWrong();
                    break;
                case 5:
                    setCorrect();
                    queTxt.setText(getString(R.string.similarScore)+" "+score+"/5");
                    String simPicScore=score+"/5";
                    Map<String,Object>simPicInfo=new HashMap<>();
                    simPicInfo.put("similarPictureScore",simPicScore);
                    mFirestore.collection("Users").document(UID).update(simPicInfo);
                    break;
            }
        }
    });

    img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuest();
                resTxt.setVisibility(View.VISIBLE);
                switch (qnum){
                    case 1:
                        setWrong();
                        break;
                    case 2:
                        setCorrect();
                        break;
                    case 3:
                        setWrong();
                        break;
                    case 4:
                        setWrong();
                        break;
                    case 5:
                        setWrong();
                        queTxt.setText(getString(R.string.similarScore)+" "+score+"/5");
                        String simPicScore=score+"/5";
                        Map<String,Object>simPicInfo=new HashMap<>();
                        simPicInfo.put("similarPictureScore",simPicScore);
                        mFirestore.collection("Users").document(UID).update(simPicInfo);
                        break;
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuest();
                resTxt.setVisibility(View.VISIBLE);
                switch (qnum){
                    case 1:
                        setWrong();
                        break;
                    case 2:
                        setWrong();
                        break;
                    case 3:
                        setCorrect();
                        break;
                    case 4:
                        setCorrect();
                        break;
                    case 5:
                        setWrong();
                        queTxt.setText(getString(R.string.similarScore)+" "+score+"/5");
                        String simPicScore=score+"/5";
                        Map<String,Object>simPicInfo=new HashMap<>();
                        simPicInfo.put("similarPictureScore",simPicScore);
                        mFirestore.collection("Users").document(UID).update(simPicInfo);
                        break;
                }
            }
        });










    }
    //1.1,2.2,3.3,4.3,5.1
    private void setQuest(){
        switch(qnum){
            case 1:
                queImg.setImageDrawable(getDrawable(R.drawable.housequest));
                img1.setImageDrawable(getDrawable(R.drawable.housewrong1));
                img2.setImageDrawable(getDrawable(R.drawable.housecorrect));
                img3.setImageDrawable(getDrawable(R.drawable.housewrong2));
                break;
            case 2:
                queImg.setImageDrawable(getDrawable(R.drawable.manquest));
                img1.setImageDrawable(getDrawable(R.drawable.manwrong1));
                img2.setImageDrawable(getDrawable(R.drawable.manwrong2));
                img3.setImageDrawable(getDrawable(R.drawable.mancorrect));
                break;
            case 3:
                queImg.setImageDrawable(getDrawable(R.drawable.treequest));
                img1.setImageDrawable(getDrawable(R.drawable.treewrong1));
                img2.setImageDrawable(getDrawable(R.drawable.treewrong2));
                img3.setImageDrawable(getDrawable(R.drawable.treecorrect));
                break;
            case 4:
                queImg.setImageDrawable(getDrawable(R.drawable.truckquest));
                img1.setImageDrawable(getDrawable(R.drawable.truckcorrect));
                img2.setImageDrawable(getDrawable(R.drawable.truckwrong1));
                img3.setImageDrawable(getDrawable(R.drawable.truckwrong2));
                break;
            case 5:
                queImg.setVisibility(View.INVISIBLE);
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.INVISIBLE);
                img3.setVisibility(View.INVISIBLE);
                break;
        }

    }
    private void setCorrect(){
        resTxt.setText(getString(R.string.similarCorrect));
        resTxt.setBackgroundColor(getColor(R.color.deep_purple));
        resTxt.setTextColor(getColor(R.color.green));
        qnum++;
        score++;
    }
    private void setWrong(){
        resTxt.setText(getString(R.string.similarWrong));
        resTxt.setBackgroundColor(getColor(R.color.deep_purple));
        resTxt.setTextColor(getColor(R.color.red));
        qnum++;
    }


}













