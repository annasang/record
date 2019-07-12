package com.mercy.dona.hi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "trusted_contacts";
    public static final String COL1 = "Contact";
    public static final String COL2 = "Location_Passcode";
    public static final String COL3 = "Ringer_Passcode";
    public static final String COL4= "Image";
    public static final String COL5="lat";
    public static final String COL6="lon";
    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 2);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String  createtable="create table trusted_contacts (Contact INTEGER  PRIMARY KEY AUTOINCREMENT,Location_Passcode TEXT(40) NOT NULL ,Ringer_Passcode DATE(8) NOT NULL,Image BLOG NOT NULL,lat TEXT(50),lon TEXT(50))";
        //  db.execSQL("create table " + TABLE_NAME + " (" + COL1 + " TEXT PRIMARY KEY , " + COL2 + " TEXT NOT NULL , " + COL3 + " TEXT NOT NULL );");
        //Toast.makeText(DataBaseHelper.this, "not", Toast.LENGTH_SHORT).show();
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String gpspass, String ringerpass,byte[] img,String lat,String lon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL1,number);
        contentValues.put(COL2,gpspass);
        contentValues.put(COL3,ringerpass);
        contentValues.put(COL4,img);
        contentValues.put(COL5,lat);
        contentValues.put(COL6,lon);

        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        //  Toast.makeText(DataBaseHelper.this,String.valueOf(result),Toast.LENGTH_LONG).show();
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDAta(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
    public Cursor dis(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME +" where Location_Passcode = " + "'" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;


    }

    public boolean updateData(String contact, String gps , String ringer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,contact);
        contentValues.put(COL2,gps);
        contentValues.put(COL3,ringer);

        db.update(TABLE_NAME,contentValues,"Contact = ? ", new String[] {contact});
        return true;
    }

    public Integer deletedata (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"Contact = ? " , new String[] { id });
    }

    public boolean existData(String phoneNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where Contact = " + "'" + phoneNumber + "'";
        Cursor cursor = db.rawQuery(Query,null);

        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        else
        {
            cursor.close();
            return true;
        }
    }

    public boolean checkgps(String phoneNumber, String gpscode){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select Location_Passcode from " + TABLE_NAME + " where Contact = " + "'" + phoneNumber + "'";
        Cursor cursor = db.rawQuery(Query,null);
        String code="";
        if (cursor.moveToFirst()) {
            code = cursor.getString(cursor.getColumnIndex("Location_Passcode"));
        }

        if (gpscode.contains(code)){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }

    public boolean checkringer(String phoneNumber, String ringercode){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select Ringer_Passcode from " + TABLE_NAME + " where Contact = " + "'" + phoneNumber + "'";
        Cursor cursor = db.rawQuery(Query,null);
        String code="";
        if (cursor.moveToFirst()) {
            code = cursor.getString(cursor.getColumnIndex("Ringer_Passcode"));
        }

        if (ringercode.contains(code)){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }
}