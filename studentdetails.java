package com.example.feesstructure;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class studentdetails extends AppCompatActivity {
    private ArrayAdapter adapte;
    ArrayList<student> list;
    SearchView searchView;
    Button b1;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);
        searchView=findViewById(R.id.search);
        b1=(Button)findViewById(R.id.add);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      mRecyclerView.setHasFixedSize(true);
      new FirebaseDatabaseHelper().read(new FirebaseDatabaseHelper.DataStatus() {
          @Override
          public void DataIsLoader(List<student> students, List<String> keys) {
                  new RecyclerView_Config().setConfig(mRecyclerView,studentdetails.this,students,keys);
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

    }
    public void button_add(View view){
        Intent i=new Intent(studentdetails.this,student_details2.class);
        startActivity(i);
    }
    }
