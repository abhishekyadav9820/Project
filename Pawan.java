package com.example.feesstructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pawan extends AppCompatActivity {
    private TextView reffv;
    private TextView namev;
    FirebaseAuth auth;
    FirebaseUser user;
    private String key;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawan);
        auth=FirebaseAuth.getInstance();
        reffv = (TextView) findViewById(R.id.ReffIdView4);
        namev = (TextView) findViewById(R.id.nameView4);
        user=auth.getCurrentUser();

        reffv.setText(user.getEmail());
        reference=FirebaseDatabase.getInstance().getReference().child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String rfid=dataSnapshot.child("rfid").getValue().toString();
                String pass=dataSnapshot.child("password").getValue().toString();

                reffv.setText(rfid);
                namev.setText(pass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}
