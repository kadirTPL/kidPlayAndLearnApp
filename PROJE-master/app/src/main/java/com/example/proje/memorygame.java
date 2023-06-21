package com.example.proje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class memorygame extends AppCompatActivity {
    private TextView numbertext;
    private EditText guessinput;
    private Button start;
    private Button submit;
    private TextView result;
    private TextView order;
    private Button tryagain;
    private TextView scoretext;
    private int[] orderofint = new int[10];
    private int index = -1;
    private int index2 = 1;
    private int score;
    String UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorygame);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        numbertext = findViewById(R.id.memorygamenumbers1);
        guessinput = findViewById(R.id.memorygameinput);
        start = findViewById(R.id.memorygamestartbutton);
        submit = findViewById(R.id.memorygamesubmitbutton);
        result = findViewById(R.id.memorygameresult);
        order = findViewById(R.id.ordertext);
        tryagain = findViewById(R.id.memorygametryagainbutton);
        scoretext = findViewById(R.id.memorygamescore);
        order.setVisibility(View.GONE);
        numbertext.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);
        guessinput.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        result.setVisibility(View.GONE);
        randomarray();
        scoretext.setText(getString(R.string.memorygamescore,score));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbertext.setVisibility(View.VISIBLE);
                index++;
                shownexti();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void randomarray() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            orderofint[i] = random.nextInt(9);
        }
    }

    private void shownexti() {
        if (index < 10) {
            numbertext.setText(String.valueOf(orderofint[index]));
        } else {
            order.setVisibility(View.GONE);
            numbertext.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
            tryagain.setVisibility(View.GONE);
            guessinput.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            result.setVisibility(View.VISIBLE);
            guessinput.setHint(getString(R.string.memorygameanswerhint, getOrdinal(index2)));
        }
    }

    private void check() {
        String guess = guessinput.getText().toString().trim();
        if (guess.isEmpty()) {
            result.setText(getString(R.string.memorygamenoanswer));
        } else {
            int answer = Integer.parseInt(guess);
            if (index2 <= 10 && orderofint[index2-1] == answer) {
                result.setText(getString(R.string.memorygamecorrect));
                score += 10;
                scoretext.setText(getString(R.string.memorygamescore,score));
            } else {
                result.setText(getString(R.string.memorygamewrong));
            }
            index2++;
            guessinput.setHint(getString(R.string.memorygameanswerhint , getOrdinal(index2)));
            guessinput.setText("");
            if(index2 == 11){
                end();
            }
        }
    }

    private void end(){
        order.setVisibility(View.VISIBLE);
        order.setText(getString(R.string.orderlist,orderofint[0],orderofint[1],orderofint[2],orderofint[3],orderofint[4],orderofint[5],orderofint[6],orderofint[7],orderofint[8],orderofint[9]));
        numbertext.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        tryagain.setVisibility(View.VISIBLE);
        guessinput.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
        scoretext.setText(getString(R.string.memorygamefinalscore,score));
        String memoryScore=score+"/100";
        Map<String,Object> memoryInfo=new HashMap<>();
        memoryInfo.put("memoryScore",memoryScore);
        mFirestore.collection("Users").document(UID).update(memoryInfo);
    }


    private void reset() {
        index = 0;
        index2 = 1;
        score = 0;
        guessinput.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        order.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        scoretext.setText(getString(R.string.memorygamescore,score));
        result.setVisibility(View.GONE);
        result.setText(" ");
        randomarray();
        numbertext.setVisibility(View.VISIBLE);
        numbertext.setText(String.valueOf(orderofint[index]));
    }

    private String getOrdinal(int number) {
        String[] ordinals = getResources().getStringArray(R.array.ordinals);
        if (number==1) {
            return number + ordinals[1];
        } else if (number==2) {
            return number + ordinals[2];
        } else if (number==3) {
            return number + ordinals[3];
        } else {
            return number + ordinals[0];
        }
    }
}
