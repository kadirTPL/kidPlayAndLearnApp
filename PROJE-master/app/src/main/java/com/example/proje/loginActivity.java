package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class loginActivity extends AppCompatActivity {
    EditText eMailedtxt, Pwordedtxt;
    Button lBtn, rBtn;
    String email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    private ImageButton tr;
    private ImageButton en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tr = findViewById(R.id.imageButtonlogtr);
        en = findViewById(R.id.imageButtonlogeng);
        eMailedtxt = (EditText) findViewById(R.id.editEmailogin);
        Pwordedtxt = (EditText) findViewById(R.id.editPasswordlogin);
        lBtn = (Button) findViewById(R.id.loginBTN);
        rBtn = (Button) findViewById(R.id.regBTN);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        lBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = eMailedtxt.getText().toString();
                password = Pwordedtxt.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(loginActivity.this, getString(R.string.loginSuccess), Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    finish();
                                    Intent i = new Intent(loginActivity.this, MainActivity.class);
                                    i.putExtra("UID", user.getUid().toString());
                                    startActivity(i);

                                } else {
                                    Pwordedtxt.setText("");
                                    Toast.makeText(loginActivity.this, getString(R.string.loginFail),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = eMailedtxt.getText().toString();
                password = Pwordedtxt.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(loginActivity.this, getString(R.string.regSuccess),
                                            Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Map<String, Object> userInfo = new HashMap<>();
                                    userInfo.put("Email", email);
                                    userInfo.put("Password", password);
                                    mFirestore.collection("Users").document(user.getUid().toString()).set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                                } else if (password.length() < 6) {
                                    Pwordedtxt.setHint(getString(R.string.passwordRule));
                                    Pwordedtxt.setHintTextColor(getColor(R.color.hint_red));
                                } else {
                                    Toast.makeText(loginActivity.this, getString(R.string.regFail),
                                            Toast.LENGTH_SHORT).show();
                                    Pwordedtxt.setText("");
                                }
                            }
                        });
            }
        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("tr");
                recreate();
            }
        });

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
                recreate();
            }
        });
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
