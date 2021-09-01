package com.example.feesstructure;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentView extends AppCompatActivity {
   private TextView reffv;
   private TextView namev;
   private TextView deparv;
   private TextView semv;
   private TextView emailv;
   private TextView dojv;
   private TextView contactv;

   private String key;
   DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentdata);
        reffv = (TextView) findViewById(R.id.ReffIdView);
        namev = (TextView) findViewById(R.id.nameView);
        deparv = (TextView) findViewById(R.id.depatrmentView);
        semv = (TextView) findViewById(R.id.semView);
        emailv = (TextView) findViewById(R.id.emailView);
        dojv = (TextView) findViewById(R.id.dojView);
        contactv = (TextView) findViewById(R.id.contactView);

        reference = FirebaseDatabase.getInstance().getReference().child("student");
        key = getIntent().getStringExtra("key");

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object object = dataSnapshot.child("rfid").getValue();
                String name = dataSnapshot.child("name").getValue().toString();
                String department = dataSnapshot.child("department").getValue().toString();
                Object sem = dataSnapshot.child("sem").getValue();
                Object email = dataSnapshot.child("email").getValue();
                String doj = dataSnapshot.child("doj").getValue().toString();
                Object contact = dataSnapshot.child("contact").getValue();


                reffv.setText("" + object);
                namev.setText(name);
                deparv.setText(department);
                semv.setText("" + sem);
                emailv.setText("" + email);
                dojv.setText(doj);
                contactv.setText("" + contact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

