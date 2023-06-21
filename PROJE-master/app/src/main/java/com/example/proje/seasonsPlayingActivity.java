package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class seasonsPlayingActivity extends AppCompatActivity {

    ImageView img;
    Button sumBut,sprBut,winBut,autBut;
    TextView resTxt,queTxt;
    Integer Qnum=1,score=0;
    String UID;
    FirebaseFirestore mFireStore=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_playing);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        img=(ImageView) findViewById(R.id.seasPimg);
        resTxt=(TextView) findViewById(R.id.seasPtxt1);
        queTxt=(TextView) findViewById(R.id.seasPtxt2);
        sumBut=(Button) findViewById(R.id.seasBut1);
        sprBut=(Button) findViewById(R.id.seasBut2);
        winBut=(Button) findViewById(R.id.seasBut4);
        autBut=(Button) findViewById(R.id.seasBut3);
        sumBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(Qnum){
                    case 1:
                        img.setImageDrawable(getDrawable(R.drawable.summer));
                        resTxt.setVisibility(View.VISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLAutumn));
                        break;
                    case 2:
                        img.setImageDrawable(getDrawable(R.drawable.spring));
                        resTxt.setText(getString(R.string.seasonPcorrect));
                        score++;
                        break;
                    case 3:
                        img.setImageDrawable(getDrawable(R.drawable.winter));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSpring));
                        break;
                    case 4:
                        img.setVisibility(View.INVISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLWinter));
                        sumBut.setVisibility(View.INVISIBLE);
                        sprBut.setVisibility(View.INVISIBLE);
                        autBut.setVisibility(View.INVISIBLE);
                        winBut.setVisibility(View.INVISIBLE);
                        String seasScore=score.toString()+"/4";
                        Map<String,Object>seasInfo=new HashMap<>();
                        seasInfo.put("seasonsScore",seasScore);
                        mFireStore.collection("Users").document(UID).update(seasInfo);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        break;
                }
                Qnum++;
            }
        });
        sprBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(Qnum){
                    case 1:
                        img.setImageDrawable(getDrawable(R.drawable.summer));
                        resTxt.setVisibility(View.VISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLAutumn));

                        break;
                    case 2:
                        img.setImageDrawable(getDrawable(R.drawable.spring));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSummer));
                        break;
                    case 3:
                        img.setImageDrawable(getDrawable(R.drawable.winter));
                        resTxt.setText(getString(R.string.seasonPcorrect));
                        score++;
                        break;
                    case 4:
                        img.setVisibility(View.INVISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLWinter));
                        sumBut.setVisibility(View.INVISIBLE);
                        sprBut.setVisibility(View.INVISIBLE);
                        autBut.setVisibility(View.INVISIBLE);
                        winBut.setVisibility(View.INVISIBLE);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        String seasScore=score.toString()+"/4";
                        Map<String,Object>seasInfo=new HashMap<>();
                        seasInfo.put("seasonsScore",seasScore);
                        mFireStore.collection("Users").document(UID).update(seasInfo);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        break;
                }
                Qnum++;
            }
        });
        autBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(Qnum){
                    case 1:
                        img.setImageDrawable(getDrawable(R.drawable.summer));
                        resTxt.setVisibility(View.VISIBLE);
                        resTxt.setText(getString(R.string.seasonPcorrect));
                        score++;
                        break;
                    case 2:
                        img.setImageDrawable(getDrawable(R.drawable.spring));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSummer));
                        break;
                    case 3:
                        img.setImageDrawable(getDrawable(R.drawable.winter));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSpring));
                        break;
                    case 4:
                        img.setVisibility(View.INVISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLWinter));
                        sumBut.setVisibility(View.INVISIBLE);
                        sprBut.setVisibility(View.INVISIBLE);
                        autBut.setVisibility(View.INVISIBLE);
                        winBut.setVisibility(View.INVISIBLE);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        String seasScore=score.toString()+"/4";
                        Map<String,Object>seasInfo=new HashMap<>();
                        seasInfo.put("seasonsScore",seasScore);
                        mFireStore.collection("Users").document(UID).update(seasInfo);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        break;
                }
                Qnum++;
            }
        });
        winBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(Qnum){
                    case 1:
                        img.setImageDrawable(getDrawable(R.drawable.summer));
                        resTxt.setVisibility(View.VISIBLE);
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLAutumn));
                        break;
                    case 2:
                        img.setImageDrawable(getDrawable(R.drawable.spring));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSummer));
                        break;
                    case 3:
                        img.setImageDrawable(getDrawable(R.drawable.winter));
                        resTxt.setText(getString(R.string.seasonPwrong)+" "+getString(R.string.seasonLSpring));
                        break;
                    case 4:
                        img.setVisibility(View.INVISIBLE);
                        resTxt.setText(getString(R.string.seasonPcorrect));
                        score++;
                        sumBut.setVisibility(View.INVISIBLE);
                        sprBut.setVisibility(View.INVISIBLE);
                        autBut.setVisibility(View.INVISIBLE);
                        winBut.setVisibility(View.INVISIBLE);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        String seasScore=score.toString()+"/4";
                        Map<String,Object>seasInfo=new HashMap<>();
                        seasInfo.put("seasonsScore",seasScore);
                        mFireStore.collection("Users").document(UID).update(seasInfo);
                        queTxt.setText(getString(R.string.seasonPlastmsg)+score+"/4");
                        break;
                }
            Qnum++;
            }
        });








    }
}