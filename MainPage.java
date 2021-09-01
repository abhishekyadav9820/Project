package com.example.feesstructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainPage extends AppCompatActivity {
    EditText e1, e2;
    DatabaseReference reference;
    Button b1;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        e1 = (EditText) findViewById(R.id.reff);
        e2 = (EditText) findViewById(R.id.stpassword);
        reference = FirebaseDatabase.getInstance().getReference().child("student");
        key = getIntent().getStringExtra("key");
        b1 = (Button) findViewById(R.id.LoginSubmit);
    }
        public void loginsubmit(View view) {
            String reffID = e1.getText().toString();
            final String password = e2.getText().toString();

            if (TextUtils.isEmpty(reffID)) {
                Toast.makeText(getApplicationContext(), "Enter ReffId address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            reference.child("student").orderByChild("refid").equalTo(e1.getText().toString().trim());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        student usersBean = user.getValue(student.class);
                        if (password.equals(usersBean.getPassword())) {
                            Toast.makeText(MainPage.this, "Login in sucessfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainPage.this, Pawan.class);
                            startActivity(intent);

                        }

                    }}


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainPage.this, "Error", Toast.LENGTH_LONG).show();

                }

            });
        }



    public void admin(View view){
        Intent i=new Intent(MainPage.this,admin.class);
        startActivity(i);
    }



    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}
