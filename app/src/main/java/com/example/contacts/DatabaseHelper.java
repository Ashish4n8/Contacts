package com.example.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context,"ashish.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ct = "CREATE TABLE CONTACTS (ID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, LNAME TEXT, NUM1 TEXT, NUM2 TEXT, EMAIL TEXT, CATEGORY TEXT, FAVORITE BOOLEAN DEFAULT 0);";
        sqLiteDatabase.execSQL(ct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        return true;
    }
}
