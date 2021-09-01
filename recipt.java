package com.example.feesstructure;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class recipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipt);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Recipt");

    }
}