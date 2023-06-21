package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ScoreboardActivity extends AppCompatActivity {
    String UID;
    TextView txtClk,txtSeas,txtDay,txtMonth,txtMem,txtRMem,txtSpell,txtDirect,txtMult,txtSim;
    FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    DocumentReference mDocument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        Bundle extras=getIntent().getExtras();
        UID=extras.getString("UID");
        mDocument=mFirestore.collection("Users").document(UID);
        txtClk=(TextView) findViewById(R.id.scrTxt1);
        txtSeas=(TextView) findViewById(R.id.scrTxt2);
        txtDay=(TextView) findViewById(R.id.scrTxt3);
        txtMonth=(TextView) findViewById(R.id.scrTxt4);
        txtMem=(TextView) findViewById(R.id.scrTxt5);
        txtRMem=(TextView) findViewById(R.id.scrTxt6);
        txtSpell=(TextView) findViewById(R.id.scrTxt7);
        txtDirect=(TextView) findViewById(R.id.scrTxt8);
        txtMult=(TextView) findViewById(R.id.scrTxt9);
        txtSim=(TextView) findViewById(R.id.scrTxt10);
        mDocument.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.getData().containsKey("clockScore"))
                {
                    txtClk.setText(documentSnapshot.getData().get("clockScore").toString());
                    txtClk.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("seasonsScore"))
                {
                    txtSeas.setText(documentSnapshot.getData().get("seasonsScore").toString());
                    txtSeas.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("dayScore"))
                {
                    txtDay.setText(documentSnapshot.getData().get("dayScore").toString());
                    txtDay.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("monthScore"))
                {
                    txtMonth.setText(documentSnapshot.getData().get("monthScore").toString());
                    txtMonth.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("memoryScore"))
                {
                    txtMem.setText(documentSnapshot.getData().get("memoryScore").toString());
                    txtMem.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("reverseMemoryScore"))
                {
                    txtRMem.setText(documentSnapshot.getData().get("reverseMemoryScore").toString());
                    txtRMem.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("wordsScore"))
                {
                    txtSpell.setText(documentSnapshot.getData().get("wordsScore").toString());
                    txtSpell.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("directionsScore"))
                {
                    txtDirect.setText(documentSnapshot.getData().get("directionsScore").toString());
                    txtDirect.setTextSize(24);
                }
                if(documentSnapshot.getData().containsKey("multipleScore"))
                {
                    txtMult.setText(documentSnapshot.getData().get("multipleScore").toString());
                    txtMult.setTextSize(24);
                }

                if(documentSnapshot.getData().containsKey("similarPictureScore"))
                {
                    txtSim.setText(documentSnapshot.getData().get("similarPictureScore").toString());
                    txtSim.setTextSize(24);
                }
            }
        });


    }
}