package com.example.contacts;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String Fname, Lname, num1, num2,email, category;
    private boolean favorite = false;

    public Contact(int id, String fname, String lname, String num1, String num2, String email, String category, boolean favorite) {
        this.id = id;
        Fname = fname;
        Lname = lname;
        this.num1 = num1;
        this.num2 = num2;
        this.email = email;
        this.category = category;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
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
