package com.example.feesstructure;



import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;


public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference reference;
    private List<student>  students= new ArrayList<>();


    public interface DataStatus{
        void DataIsLoader(List<student> students,List<String>keys);
        void DataIsInserted();
        void DataIsUpdate();
        void DataIsDelete();
    }

    public FirebaseDatabaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        reference=mDatabase.getReference("student");


    }
    public void read(final DataStatus dataStatus)
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students.clear();
                List<String> Keys=new ArrayList<>();
                for(DataSnapshot KeyNode:dataSnapshot.getChildren())
                {
                    Keys.add(KeyNode.getKey());
                    student student=KeyNode.getValue(student.class);
                    students.add(student);
                }
                dataStatus.DataIsLoader(students,Keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void addStudent(student student,final  DataStatus dataStatus){
        String Key= reference.push().getKey();
        reference.child(Key).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }




    public  void deleteStudent(String Key,final DataStatus dataStatus)
    {
        reference.child(Key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDelete();
                    }
                });
    }
}
