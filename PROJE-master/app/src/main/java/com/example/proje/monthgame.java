package com.example.proje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class monthgame extends AppCompatActivity {

    private TextView monthgamequestion;
    private TextView monthgameresulttext;
    private RadioGroup monthgameradiogroup;
    private Button monthgamesubmitbutton;
    private Button monthgametryagainbutton;
    private List<String> months;
    private int currentQuestion;
    private int correctAnswerCount;
    String UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthgame);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        monthgamequestion = findViewById(R.id.monthgamequestion);
        monthgameradiogroup = findViewById(R.id.monthgameradiogroup);
        monthgamesubmitbutton = findViewById(R.id.monthgamesubmitbutton);
        monthgameresulttext = findViewById(R.id.monthgameresulttext);
        monthgametryagainbutton = findViewById(R.id.monthgametryagainbutton);
        months = Arrays.asList(getString(R.string.JanuaryText),
                getString(R.string.FebruaryText),
                getString(R.string.MarchText),
                getString(R.string.AprilText),
                getString(R.string.MayText),
                getString(R.string.JuneText),
                getString(R.string.JulyText),
                getString(R.string.AugustText),
                getString(R.string.SeptemberText),
                getString(R.string.OctoberText),
                getString(R.string.NovemberText),
                getString(R.string.DecemberText));
        monthgamesubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        monthgametryagainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        resetGame();
    }

    private void resetGame() {
        currentQuestion = 0;
        correctAnswerCount = 0;
        monthgameresulttext.setVisibility(View.GONE);
        monthgametryagainbutton.setVisibility(View.GONE);
        showQuestion();
        monthgamesubmitbutton.setEnabled(true);
    }

    private void showQuestion() {
        monthgamequestion.setText(getString(R.string.monthgamequestion, getOrdinal(currentQuestion + 1)));


        monthgameradiogroup.clearCheck();
        monthgameradiogroup.removeAllViews();

        List<String> answerOptions = generateAnswer();
        for (String option : answerOptions) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            monthgameradiogroup.addView(radioButton);
        }
    }

    private List<String> generateAnswer() {
        List<String> answerOptions = new ArrayList<>(months);
        String correctAnswer = months.get(currentQuestion);
        answerOptions.remove(correctAnswer);
        Collections.shuffle(answerOptions);
        answerOptions = answerOptions.subList(0, 2);
        int randomIndex = new Random().nextInt(3);
        answerOptions.add(randomIndex, correctAnswer);
        return answerOptions;
    }

    private void checkAnswer() {
        int selectedRadioButtonId = monthgameradiogroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            String correctAnswer = months.get(currentQuestion);
            if (selectedAnswer.equals(correctAnswer)) {
                correctAnswerCount++;
                monthgameresulttext.setText(getString(R.string.monthgamecorrect));

            } else {
                monthgameresulttext.setText(getString(R.string.monthgamewrong, correctAnswer, correctAnswerCount));
                monthgametryagainbutton.setVisibility(View.VISIBLE);
                monthgamesubmitbutton.setEnabled(false);
                String monthScore=correctAnswerCount+"/12";
                Map<String,Object> monthInfo=new HashMap<>();
                monthInfo.put("monthScore",monthScore);
                mFirestore.collection("Users").document(UID).update(monthInfo);
            }

            currentQuestion++;
            if (currentQuestion < 12) {
                showQuestion();
            } else {
                if (correctAnswerCount == 12) {
                    monthgameresulttext.setText(getString(R.string.monthgameamazing));
                    monthgametryagainbutton.setVisibility(View.VISIBLE);
                    monthgamesubmitbutton.setEnabled(false);
                } else {
                    monthgametryagainbutton.setVisibility(View.VISIBLE);
                    monthgamesubmitbutton.setEnabled(false);
                }
                String monthScore=correctAnswerCount+"/12";
                Map<String,Object> monthInfo=new HashMap<>();
                monthInfo.put("monthScore",monthScore);
                mFirestore.collection("Users").document(UID).update(monthInfo);
            }
            monthgameresulttext.setVisibility(View.VISIBLE);
        }
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