package com.example.contacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> contacts;
//    private DatabaseHelper db;
    private ContactRepositoy repo;

    public ContactViewModel(@NonNull Application application) {
        super(application);
//        db = new DatabaseHelper(application);
        repo = new ContactRepositoy(application);
        contacts = repo.getallcontacts();

    }

    public void insert(Contact contact){
//        boolean q = db.addContact(contact);
//        return q;
        repo.insert(contact);
    }
    public void update(Contact contact){
//        boolean qu = db.updateContact(contact);
//        return qu;
        repo.update(contact);
    }
    public void delete(Contact contact){
        repo.delete(contact);
    }
    public LiveData<List<Contact>> getall(){
        /*if (contacts == null){
            contacts = new MutableLiveData<ArrayList<Contact>>();
        }
        contacts = db.getContacts();*/
        return contacts;
    }
}
