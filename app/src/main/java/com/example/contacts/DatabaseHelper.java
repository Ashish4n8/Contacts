package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public boolean addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FNAME", contact.getFname());
        cv.put("LNAME", contact.getLname());
        cv.put("NUM1", contact.getNum1());
        cv.put("NUM2", contact.getNum2());
        cv.put("EMAIL", contact.getEmail());
        cv.put("CATEGORY", contact.getCategory());
        cv.put("FAVORITE", false);
        long insert = db.insert("CONTACTS", null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String query = "SELECT * FROM CONTACTS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String num1 = cursor.getString(3);
                String num2 = cursor.getString(4);
                String email = cursor.getString(5);
                String cat = cursor.getString(6);
                int f = cursor.getInt(7);
                boolean fav;
                if (f == 0){
                    fav = false;
                }
                else fav=true;
                Contact cont = new Contact(id,fname,lname,num1,num2,email,cat,fav);
                contacts.add(cont);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
