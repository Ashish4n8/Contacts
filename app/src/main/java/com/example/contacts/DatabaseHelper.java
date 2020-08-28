package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    MutableLiveData<ArrayList<Contact>> contacts;
    ArrayList<Contact> list;

    public DatabaseHelper(@Nullable Context context) {
        super(context,"ashish.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ct = "CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, LNAME TEXT, NUM1 TEXT, NUM2 TEXT, EMAIL TEXT, CATEGORY TEXT, FAVORITE BOOLEAN DEFAULT 0, IMAGE BLOB);";
        sqLiteDatabase.execSQL(ct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String ima = contact.getImage();
        ByteArrayOutputStream str = new ByteArrayOutputStream();
//        ima.compress(Bitmap.CompressFormat.JPEG, 100, str);
//        byte[] image = str.toByteArray();
        ContentValues cv = new ContentValues();
        cv.put("FNAME", contact.getFname());
        cv.put("LNAME", contact.getLname());
        cv.put("NUM1", contact.getNum1());
        cv.put("NUM2", contact.getNum2());
        cv.put("EMAIL", contact.getEmail());
        cv.put("CATEGORY", contact.getCategory());
        cv.put("FAVORITE", false);
        cv.put("IMAGE",ima);
        long insert = db.insert("CONTACT", null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            list.add(new Contact(contact.getFname(),contact.getLname(),contact.getNum1(),contact.getNum2(),contact.getEmail(),contact.getCategory(),contact.isFavorite(),contact.getImage()));
            return true;
        }
    }

    public boolean updateContact(Contact contact){
        SQLiteDatabase database = this.getWritableDatabase();
        String ima = contact.getImage();
//        ByteArrayOutputStream str = new ByteArrayOutputStream();
//        ima.compress(Bitmap.CompressFormat.JPEG, 100, str);
//        byte[] image = str.toByteArray();
        ContentValues cv = new ContentValues();
        cv.put("FNAME",contact.getFname());
        cv.put("LNAME", contact.getLname());
        cv.put("NUM1", contact.getNum1());
        cv.put("NUM2", contact.getNum2());
        cv.put("EMAIL", contact.getEmail());
        cv.put("CATEGORY", contact.getCategory());
        cv.put("FAVORITE", contact.isFavorite());
        cv.put("IMAGE",ima);
        long up = database.update("CONTACT",cv,"ID="+contact.getId(),null);
        if (up == -1){
            return false;
        }
        else {
            //list = getContacts();
            return true;
        }
    }

    public MutableLiveData<ArrayList<Contact>> getContacts(){

        contacts = new MutableLiveData<ArrayList<Contact>>();
        list = new ArrayList<Contact>();
        String query = "SELECT * FROM CONTACT";
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
                String ima = cursor.getString(8);
//                String ima = BitmapFactory.decodeByteArray(im,0,im.length);
                boolean fav;
                if (f == 0){
                    fav = false;
                }
                else fav=true;
                Contact cont = new Contact(fname,lname,num1,num2,email,cat,fav,ima);
                list.add(cont);

                Log.d("ID start","ID Start");
                Log.d("ID =", String.valueOf(id));
            }while (cursor.moveToNext());
        }
        cursor.close();
        contacts.postValue(list);
        db.close();
        return contacts;
    }
}
