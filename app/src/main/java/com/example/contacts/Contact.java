package com.example.contacts;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "contact_table")
public class Contact{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "First_name")
    private String fname;

    @ColumnInfo(name = "Last_name")
    private String lname;

    @ColumnInfo(name = "Num_1")
    private String num1;

    @ColumnInfo(name = "Num_2")
    private String num2;

    @ColumnInfo(name = "E_mail")
    private String email;

    @ColumnInfo(name = "Category")
    private String category;

    @ColumnInfo(name = "Favorite")
    private boolean favorite = false;

    @ColumnInfo(name = "Photo")
    private String image;

    public Contact(String fname, String lname, String num1, String num2, String email, String category, boolean favorite, String image) {
        this.fname = fname;
        this.lname = lname;
        this.num1 = num1;
        this.num2 = num2;
        this.email = email;
        this.category = category;
        this.favorite = favorite;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

}
