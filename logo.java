package com.example.feesstructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class logo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2 * 1000);
                    Intent i = new Intent(getBaseContext(),MainPage.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();

    }
    protected void onDestroye()
    {
        super.onDestroy();
    }
}

