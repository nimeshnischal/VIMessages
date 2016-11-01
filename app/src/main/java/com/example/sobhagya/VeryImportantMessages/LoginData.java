package com.example.sobhagya.VeryImportantMessages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NMZ on 06-07-2016.
 */
public class LoginData extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "logindatabase";
 //   public static final String KEY_ID = "id";
    public static final String TABLE_NAME = "logintable";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PIN = "pin";
    public LoginData(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( username text, password text, pin text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean signup(String us_username, String us_password){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 0, 1);
        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_ID,1);
        contentValues.put(USERNAME,us_username);
        contentValues.put(PASSWORD,us_password);
        db.insert(TABLE_NAME, null, contentValues);
/*
        String db_pin = new String();
        Cursor res = db.rawQuery("select pin from "+TABLE_NAME,null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append(res.getString(0));
        }
        db_pin = buffer.toString();


*/
        return true;
    }

    public Boolean pin(String us_pin){
        SQLiteDatabase db = this.getWritableDatabase();
//        String db_username = new String();
//        Cursor res = db.rawQuery("select username from "+TABLE_NAME ,null);
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append(res.getString(0));
//        }
//        db_username = buffer.toString();
        db.execSQL(" UPDATE " + TABLE_NAME + " SET " + PIN + " = " + us_pin);
        return true;

    }
    public Boolean login(String us_pin){
        SQLiteDatabase db = this.getWritableDatabase();
        String db_pin = new String();
        Cursor res = db.rawQuery("select pin from "+TABLE_NAME + ";",null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append(res.getString(0));
        }
        db_pin = buffer.toString();
        if(us_pin.equals(db_pin)){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkpin(){
        SQLiteDatabase db = this.getReadableDatabase();
        String db_pin = new String();
        try {
            Cursor res = db.rawQuery("select pin from "+TABLE_NAME + ";",null);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append(res.getString(0));
            }
            db_pin = buffer.toString();
        }
        catch (Exception e){

        }
        finally {
            if(db_pin.length() != 4 || db_pin.equals("null")){
                db.delete(TABLE_NAME,null,null);
                return false;
            }
            else {
                return true;
            }
        }
    }

    public Boolean retpin(String us, String pas){
        SQLiteDatabase db = this.getReadableDatabase();
//        String db_pin = new String();
        String db_val= new String();
        String db_pas = new String();
        StringBuffer buffer = new StringBuffer();
        Cursor res = db.rawQuery("select username from " + TABLE_NAME + ";",null);
        while (res.moveToNext()){
            buffer.append(res.getString(0));
        }
        db_val = buffer.toString();
        if(!us.equals(db_val))
            return false;
        StringBuffer buffer1 = new StringBuffer();
        Cursor res1 = db.rawQuery("select password from " + TABLE_NAME + ";",null);
        while (res1.moveToNext()){
            buffer1.append(res1.getString(0));
        }
        db_pas = buffer1.toString();
        if(!pas.equals(db_pas))
            return false;
//        Cursor res2 = db.rawQuery("select pin from " + TABLE_NAME + ";",null);
//        StringBuffer buffer2 = new StringBuffer();
//        while (res2.moveToNext()){
//            buffer2.append(res2.getString(0));
//        }
//        db_pin = buffer2.toString();
        return true;
    }

    public boolean del(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
        return true;
    }

    public boolean del1(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        return true;
    }
}
