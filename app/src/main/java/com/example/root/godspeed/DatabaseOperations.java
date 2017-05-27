package com.example.root.godspeed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 12/4/17.
 */
public class DatabaseOperations extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "godspeed.db";
    public static final String TABLE_FOR_LOGIN = "login_table";
    public static final String COL_ID = "id";
    public static final String COL_UNAME = "uname";
    public static final String COL_PASS = "pass";




    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_FOR_LOGIN +"(id INTEGER PRIMARY KEY AUTOINCREMENT, uname TEXT,pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_FOR_LOGIN);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_UNAME,username);
        contentValues.put(COL_PASS,password);
        long res = db.insert(TABLE_FOR_LOGIN,null,contentValues);
        if(res == -1)return false;
        return true;
    }

    public Cursor getCred(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_FOR_LOGIN,null );
        return res;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        String temp = "1";
        db.execSQL("delete from "+ TABLE_FOR_LOGIN);
        //return db.delete(TABLE_FOR_LOGIN,"id = ?",new String[]{temp});
    }
}
