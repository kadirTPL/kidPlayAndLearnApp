package com.example.proje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class wordsplaying extends AppCompatActivity {

    private TextView wordquestioncounter;
    private TextView wordgameresulttext;
    private RadioGroup wordgameradiogroup;
    private Button wordgamesubmitbutton;
    private Button wordgametryagainbutton;
    private TextView wordgamescoretext;
    private String[] wordscorrects;
    private String[] wordswrongs1;
    private String[] wordswrongs2;
    private int currentQuestion = 1;
    private int score = 0;
    String UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    private int randomIndex = new Random().nextInt(25);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordsplaying);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        wordquestioncounter = findViewById(R.id.wordgamequestioncounter);
        wordgameresulttext = findViewById(R.id.wordgameresulttext);
        wordgameradiogroup =findViewById(R.id.wordgameradiogroup);
        wordgamesubmitbutton = findViewById(R.id.wordgamesubmitbutton);
        wordgametryagainbutton = findViewById(R.id.wordgametryagainbutton);
        wordscorrects = getResources().getStringArray(R.array.wordsgamecorrectsarray);
        wordswrongs1 = getResources().getStringArray(R.array.wordsgamewrongssarray1);
        wordswrongs2 = getResources().getStringArray(R.array.wordsgamewrongssarray2);
        wordgamescoretext = findViewById(R.id.wordgamescore);
        wordgametryagainbutton.setVisibility(View.GONE);
        wordgamescoretext.setText(getString(R.string.wordsgamescoretext, score));
        wordquestioncounter.setText(getString(R.string.wordsgameqc, currentQuestion));
        resetG();
        wordgamesubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkA();
            }
        });
        wordgametryagainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetG();
            }
        });
    }
    private void resetG() {
        currentQuestion = 1;
        score = 0;
        wordgamescoretext.setText(getString(R.string.wordsgamescoretext, score));
        wordquestioncounter.setText(getString(R.string.wordsgameqc, currentQuestion));
        wordgameresulttext.setVisibility(View.GONE);
        wordgametryagainbutton.setVisibility(View.GONE);
        showQ();
        wordgamesubmitbutton.setEnabled(true);
    }
    private void showQ() {
        wordgameradiogroup.clearCheck();
        wordgameradiogroup.removeAllViews();
        randomIndex = (int) (Math.random() * 25);
        String answer1 = wordscorrects[randomIndex];
        String answer2 = wordswrongs1[randomIndex];
        String answer3 = wordswrongs2[randomIndex];
        List<String> answers = new ArrayList<>(Arrays.asList(answer1, answer2, answer3));
        Collections.shuffle(answers);
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setId(View.generateViewId());
        radioButton1.setText(answers.get(0));
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setId(View.generateViewId());
        radioButton2.setText(answers.get(1));
        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setId(View.generateViewId());
        radioButton3.setText(answers.get(2));

        wordgameradiogroup.addView(radioButton1);
        wordgameradiogroup.addView(radioButton2);
        wordgameradiogroup.addView(radioButton3);
        wordquestioncounter.setText(getString(R.string.wordsgameqc, currentQuestion));
    }
    private void checkA() {
        int selectedRadioButtonId = wordgameradiogroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = wordscorrects[randomIndex];
            if (selectedAnswer.equals(correctAnswer)) {
                score += 10;
                wordgamescoretext.setText(getString(R.string.wordsgamescoretext, score));
                wordgameresulttext.setText(getString(R.string.wordsgamecorrectanswer));
            } else {
                wordgameresulttext.setText(getString(R.string.wordsgamewrong, correctAnswer));
            }
            wordgameresulttext.setVisibility(View.VISIBLE);
            currentQuestion++;
            if (currentQuestion < 11) {
                showQ();
            } else {
                    wordgamescoretext.setText(getString(R.string.wordsgamefinalscoretext,score));
                    wordgametryagainbutton.setVisibility(View.VISIBLE);
                    wordgamesubmitbutton.setEnabled(false);
                    String wordScore=score+"/100";
                    Map<String,Object>wordInfo=new HashMap<>();
                   wordInfo.put("wordsScore",wordScore);
                   mFirestore.collection("Users").document(UID).update(wordInfo);

                }
            }
        }
    }