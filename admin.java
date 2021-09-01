package com.example.feesstructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.google.firebase.auth.FirebaseUser;



public class admin extends AppCompatActivity  {
    EditText edtuser, edtpass;
    Button btnLogin;
    FirebaseAuth  auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_admin);
        edtuser = (EditText) findViewById(R.id.User);
        edtpass = (EditText) findViewById(R.id.adminpassword);
        btnLogin = (Button) findViewById(R.id.Login);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(admin.this,Home.class));
            finish();
        }

       btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtuser.getText().toString();
                final String password = edtpass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(admin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {

                                    // there was an error
                                    if (password.length() < 6) {
                                        edtpass.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(admin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(admin.this, "Login in sucessfully" ,Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(admin.this,Home.class);
                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
    public void student(View view){
        Intent i=new Intent(admin.this,MainPage.class);
        startActivity(i);
    }
}
