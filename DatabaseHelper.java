package com.example.feesstructure;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class
DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1 = "ReffId";
    public static final String COL_2 = "name";
    public static final String COL_3 = "fees";
    public static final String COL_4 = "balance";
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("Create table "+TABLE_NAME +"(ReffID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,fees INTERGER,balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP  TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name,String fees,String balance)
    {
      SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,fees);
        contentValues.put(COL_4,balance);
       long success= db.insert(TABLE_NAME,null,contentValues);
       if(success==-1){
           return  false;
       }
       else {
           return  true;
       }
     }
               public Cursor getAllData()
              {
               SQLiteDatabase db=this.getWritableDatabase();
                Cursor cur=db.rawQuery("select * from " +TABLE_NAME,null);
                return cur;
                }


                }