package com.example.feesstructure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class student_details2 extends AppCompatActivity {
    private EditText addreffid;
    private EditText addname;
    private Spinner addcla;
    private EditText addfees;
    private EditText addbalance;
    private EditText calender;
    private EditText addemail;
    private EditText addcont;
    private Button add;
    private Spinner deparment;
    Spinner classSpinner, divSpinner;
    String selectedClass, selectedDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details2);

        classSpinner = (Spinner) findViewById(R.id.spinner1);
        divSpinner = (Spinner) findViewById(R.id.spinner2);

        addreffid = (EditText) findViewById(R.id.addreffId);
        addname = (EditText) findViewById(R.id.addname);
        deparment = (Spinner) findViewById(R.id.spinner1);
        addcla = (Spinner) findViewById(R.id.spinner2);
        addfees = (EditText) findViewById(R.id.addfees);
        addbalance = (EditText) findViewById(R.id.addbalance);
        addemail = (EditText) findViewById(R.id.addemail);
        addcont = (EditText) findViewById(R.id.addcontent);

        calender = (EditText) findViewById(R.id.adddoj);
        calender.setInputType(InputType.TYPE_NULL);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(calender);
            }
        });

        add = (Button) findViewById(R.id.save);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student book = new student();
                book.setRfid(addreffid.getText().toString());
                book.setName(addname.getText().toString());
                book.setDepartment(deparment.getSelectedItem().toString());
                book.setSem(addcla.getSelectedItem().toString());
                book.setFees(addfees.getText().toString());
                book.setBalance(addbalance.getText().toString());
                book.setDoj(calender.getText().toString());
                book.setEmail(addemail.getText().toString());
                book.setContact(addcont.getText().toString());


                String reffID =addreffid.getText().toString();
                 String name = addname.getText().toString();
                String depart=deparment.getSelectedItem().toString();
                String sem=addcla.getSelectedItem().toString();
                String fees=addfees.getText().toString();
                String balance=addbalance.getText().toString();
                String cal=calender.getText().toString();
                String emai=addemail.getText().toString();
                String cont=addcont.getText().toString();

                if (TextUtils.isEmpty(reffID)) {
                    Toast.makeText(getApplicationContext(), "Please Enter RfId", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), " Please Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(depart)) {
                    Toast.makeText(getApplicationContext(), "Please Select department!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(sem)) {
                    Toast.makeText(getApplicationContext(), "Please Select Class", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fees)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Fees!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(balance)) {
                    Toast.makeText(getApplicationContext(), "Please Enter balance!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cal)) {
                    Toast.makeText(getApplicationContext(), "Please Enter calender!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(emai)) {
                    Toast.makeText(getApplicationContext(), "Please Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cont)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Contact!", Toast.LENGTH_SHORT).show();
                    return;
                }

                new FirebaseDatabaseHelper().addStudent(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoader(List<student> students, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(student_details2.this, "student add succesfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdate() {

                    }

                    @Override
                    public void DataIsDelete() {

                    }
                });


            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass) {
                    case "Department":
                        divSpinner.setAdapter(new ArrayAdapter<String>(student_details2.this,
                                android.R.layout.simple_spinner_dropdown_item
                              ));
                        break;

                    case "BSCIT":
                        Toast.makeText(student_details2.this, "Please Select Ur Year", Toast.LENGTH_LONG).show();
                        divSpinner.setAdapter(new ArrayAdapter<String>(student_details2.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_1)));
                        break;

                    case "B.COM":
                        Toast.makeText(student_details2.this, "Please Select Ur Year", Toast.LENGTH_LONG).show();
                        divSpinner.setAdapter(new ArrayAdapter<String>(student_details2.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_2)));
                        break;

                    case "B.M.S":
                        Toast.makeText(student_details2.this, "Please Select Ur Year", Toast.LENGTH_LONG).show();
                        divSpinner.setAdapter(new ArrayAdapter<String>(student_details2.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_3)));
                        break;


                }

                //set divSpinner Visibility to Visible
                divSpinner.setVisibility(View.VISIBLE);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        divSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDiv = parent.getItemAtPosition(position).toString();
                /*
                    Now that we have both values, lets create a Toast to
                    show the values on screen
                */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
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
      new DatePickerDialog(student_details2.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

}}