package com.example.proje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MultGameActivity extends AppCompatActivity {

    private TextView questionText;
    private TextView scoreText;
    private TextView errorText;
    private TextView resultText;
    private EditText guessEdit;
    private Button submitBtn;
    private int currentQuestion = 1;
    private int score = 0;
    private int num1, num2, answer;
    private static final int TOTAL_QUESTIONS = 10;
    String UID;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multgame);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        errorText = findViewById(R.id.errorText);
        resultText = findViewById(R.id.resultText);
        guessEdit = findViewById(R.id.guessEdit);
        submitBtn = findViewById(R.id.submitBtn);
        questionText.setText(getString(R.string.multgamequestiontext, currentQuestion, TOTAL_QUESTIONS));
        scoreText.setText(getString(R.string.multgamescoretext, score));
        generateQuestion();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessStr = guessEdit.getText().toString();
                if (guessStr.isEmpty()) {
                    errorText.setText(R.string.multgameerrortext);
                    return;
                }
                int guess = Integer.parseInt(guessStr);
                if (guess == answer) {
                    resultText.setText(R.string.multgamecorrecttext);
                    score += 10;
                } else {
                    resultText.setText(getString(R.string.multgamewrongtext, answer));
                }
                scoreText.setText(getString(R.string.multgamescoretext, score));
                currentQuestion++;
                if (currentQuestion <= TOTAL_QUESTIONS) {
                    questionText.setText(getString(R.string.multgamequestiontext, currentQuestion, TOTAL_QUESTIONS));
                    generateQuestion();
                } else {
                    questionText.setText(getString(R.string.multgamefinalscore, score));
                    String multScore=score+"/100";
                    Map<String,Object>multInfo=new HashMap<>();
                    multInfo.put("multipleScore",multScore);
                    mFirestore.collection("Users").document(UID).update(multInfo);
                    guessEdit.setEnabled(false);
                    submitBtn.setEnabled(false);
                    Button tryAgainBtn = new Button(MultGameActivity.this);
                    tryAgainBtn.setText(R.string.tryagainbutton);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.BELOW, R.id.resultText);
                    layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    layoutParams.setMargins(0, 32, 0, 0);
                    tryAgainBtn.setLayoutParams(layoutParams);
                    tryAgainBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentQuestion = 1;
                            score = 0;
                            scoreText.setText(getString(R.string.multgamescoretext, score));
                            guessEdit.setEnabled(true);
                            submitBtn.setEnabled(true);
                            generateQuestion();
                            questionText.setText(getString(R.string.multgamequestiontext, currentQuestion, TOTAL_QUESTIONS));
                            resultText.setText("");
                            RelativeLayout layout = findViewById(R.id.multgame);
                            layout.removeView(tryAgainBtn);
                        }
                    });
                    RelativeLayout layout = findViewById(R.id.multgame);
                    layout.addView(tryAgainBtn);
                }
                guessEdit.setText("");
                errorText.setText("");
            }
        });
    }

    private void generateQuestion() {
        Random rand = new Random();
        num1 = rand.nextInt(10) + 1;
        num2 = rand.nextInt(10) + 1;
        answer = num1 * num2;
        TextView num1TextView = findViewById(R.id.num1Text);
        TextView num2TextView = findViewById(R.id.num2Text);
        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));
    }
}
