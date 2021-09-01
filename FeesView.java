package com.example.feesstructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class FeesView extends AppCompatActivity {
    private TextView reffv;
    private TextView namev;
    private TextView deparv;
    private TextView semv;
    private TextView emailv;
    private TextView dojv;
    private TextView feesv;
    private TextView balancev;

    private String key;


    DatabaseReference reference;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_view);
        reffv = (TextView) findViewById(R.id.ReffIdView1);
        namev = (TextView) findViewById(R.id.nameView1);
        deparv = (TextView) findViewById(R.id.depatrmentView1);
        semv = (TextView) findViewById(R.id.semView1);
        emailv = (TextView) findViewById(R.id.emailView1);
        dojv = (TextView) findViewById(R.id.dojView1);
        balancev = (TextView) findViewById(R.id.balanceView1);
        feesv = (TextView) findViewById(R.id.feesView1);


        reference = FirebaseDatabase.getInstance().getReference().child("student");
        key = getIntent().getStringExtra("key");
        b1 = (Button) findViewById(R.id.pay);
        b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FeesView.this,Payment.class);
                        intent.putExtra("key", key);
                        startActivity(intent);
                    }
                });
        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object object=dataSnapshot.child("rfid").getValue();
                String name=dataSnapshot.child("name").getValue().toString();
                String department=dataSnapshot.child("department").getValue().toString();
                Object sem=dataSnapshot.child("sem").getValue();
                Object email=dataSnapshot.child("email").getValue();
                String doj=dataSnapshot.child("doj").getValue().toString();
                Object fees=dataSnapshot.child("fees").getValue();
                Object balance=dataSnapshot.child("balance").getValue();

                reffv.setText(""+object);
                namev.setText(name);
                deparv.setText(department);
                semv.setText(""+sem);
                emailv.setText(""+email);
                dojv.setText(doj);
                feesv.setText(""+fees);
                balancev.setText(""+balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }}

