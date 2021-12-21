package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");

        db.execSQL("insert into user_table values(1234567890,'Prajwal',10000.00,'Prajwal@gmail.com','XXXXXXXXXXXX7890','ABC76316485')");
        db.execSQL("insert into user_table values(1937595751,'Ravi',7629.10,'Ravi@gmail.com','XXXXXXXXXXXX5751','XYZ40310056')");
        db.execSQL("insert into user_table values(7319642850,'Vedant',9730.00,'Vedant@gmail.com','XXXXXXXXXXXX2850','PQR00004634')");
        db.execSQL("insert into user_table values(9764312864,'Rushi',8000.11,'Rushi@gmail.com','XXXXXXXXXXXX2864','MNO76134590')");
        db.execSQL("insert into user_table values(6321478950,'Yash',9643.01,'Yash@gmail.com','XXXXXXXXXXXX8950','LMN74136958')");
        db.execSQL("insert into user_table values(7309461285,'Ram',9873.09,'Ram@gmail.com','XXXXXXXXXXXX1285','OPR45301789')");
        db.execSQL("insert into user_table values(4679156031,'Akshay',500.05,'Akshay@gmail.com','XXXXXXXXXXXX6031','WXY11111111')");
        db.execSQL("insert into user_table values(9513572580,'Ajinkya',1346.11,'Ajinkya@gmail.com','XXXXXXXXXXXX2580','EFG97645555')");
        db.execSQL("insert into user_table values(7613496481,'Tushar',672.88,'Tushar@gmail.com','XXXXXXXXXXXX6481','HIJ13000546')");
        db.execSQL("insert into user_table values(7512369874,'Rohit',8080.49,'Rohit@gmail.com','XXXXXXXXXXXX9874','KLM44996633')");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
