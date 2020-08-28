package com.example.contacts;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoy {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allcontacts;

    public ContactRepositoy(Application application){
        ContactDatabase database = ContactDatabase.getInstance(application);
        contactDao = database.contactDao();
        allcontacts = contactDao.getcontacts();
    }

    public void insert(Contact contact){
        new InsertAsyncTask(contactDao).execute(contact);
    }
    public void update(Contact contact){
        new UpdateAsyncTask(contactDao).execute(contact);
    }
    public void delete(Contact contact){
        new DeleteAsyncTask(contactDao).execute(contact);
    }
    public LiveData<List<Contact>> getallcontacts(){
        return allcontacts;
    }
    private static class InsertAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private InsertAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private UpdateAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private DeleteAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
}
