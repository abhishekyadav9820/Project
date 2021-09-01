package com.example.feesstructure;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

     FirebaseAuth fa;
    Button student,fees,payment,recipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        student=(Button)findViewById(R.id.StudentButton);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Home.this,studentdetails.class);
                startActivity(i);
            }
        });
        fees=(Button)findViewById(R.id.FeesButton);
        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,FeesDetail.class);
                startActivity(i);
            }
        });
        payment=(Button)findViewById(R.id.PaymentButton);
       payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,FeesDetail.class);
                startActivity(i);
            }
        });
        recipt=(Button)findViewById(R.id.ReciptButton);
        recipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,recipt.class);
                startActivity(i);
            }
        });


        fa=FirebaseAuth.getInstance();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
if(item.getItemId()==R.id.logout){
    FirebaseAuth.getInstance().signOut();
    finish();
    Intent i=new Intent(getApplicationContext(),admin.class);
    startActivity(i);
}
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}
