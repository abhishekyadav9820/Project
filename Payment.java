package com.example.feesstructure;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Payment extends AppCompatActivity {
    private TextView reffv;
    private TextView namev;
    private TextView contacv;
    private TextView totalfees;
    private TextView balancev;
    private EditText calender;

    private String key;
    private Button payment;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_payment);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Payment");

        reffv = (TextView) findViewById(R.id.ReffIdView2);
        namev = (TextView) findViewById(R.id.nameView2);
        contacv = (TextView) findViewById(R.id.contact);
        balancev = (TextView) findViewById(R.id.paid);
        payment = (Button) findViewById(R.id.payment);

        totalfees = (TextView) findViewById(R.id.feesView2);
        calender = (EditText) findViewById(R.id.date);
        calender.setInputType(InputType.TYPE_NULL);

        reference= FirebaseDatabase.getInstance().getReference().child("student");
        key=getIntent().getStringExtra("key");
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(calender);
            }
        });

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object object = dataSnapshot.child("rfid").getValue();
                String name = dataSnapshot.child("name").getValue().toString();
                Object contact = dataSnapshot.child("contact").getValue();
                Object fees = dataSnapshot.child("fees").getValue();
                Object balance = dataSnapshot.child("balance").getValue();

                reffv.setText("" + object);
                namev.setText(name);
                totalfees.setText("" + fees);
                contacv.setText("" + contact);
                balancev.setText("" + balance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
        private void showDataDialog(final EditText calender) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM yyyy");
                calender.setText(simpleDateFormat.format(calendar.getTime()));
                }
            };

            new DatePickerDialog(Payment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    }

