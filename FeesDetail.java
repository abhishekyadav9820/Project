package com.example.feesstructure;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FeesDetail extends AppCompatActivity {
    private ArrayAdapter adapte;
    ArrayList<student> list;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fees_detail);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Fees Details");

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        new FirebaseDatabaseHelper().read(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoader(List<student> students, List<String> keys) {
                new RecyclerView_Config1().setConfig(mRecyclerView,FeesDetail.this,students,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDelete() {

            }
        });

    }}