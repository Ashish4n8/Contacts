package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailContact extends AppCompatActivity {
    ImageView img, fav;
    TextView name, numb1, numb2,msg1,msg2,mail, cate;
    LinearLayout call, msg, lMail,call1,msgL;
    Contact contact;
    ContactViewModel cvm;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        Toolbar toolbar = findViewById(R.id.toolb);
        toolbar.inflateMenu(R.menu.detail_contact);
        name = findViewById(R.id.name);
        numb1 = findViewById(R.id.numb1);
        numb2 = findViewById(R.id.numb2);
        msg1 = findViewById(R.id.msg1);
        msg2 = findViewById(R.id.msg2);
        mail = findViewById(R.id.mail);
        cate = findViewById(R.id.cate);
        call = findViewById(R.id.call);
        msg = findViewById(R.id.msg);
        lMail = findViewById(R.id.lMail);
        call1 = findViewById(R.id.call1);
        msgL = findViewById(R.id.msgL);
        fav = findViewById(R.id.fav);
        img = findViewById(R.id.dima);

        int x = getIntent().getIntExtra("contact",0);
        cvm = new ViewModelProvider(DetailContact.this).get(ContactViewModel.class);
//        contacts = new ArrayList<Contact>();
        cvm.getall().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> list) {
                Log.d("ASHISHHHHH",String.valueOf(list.size()));
                contacts =list;
            }
        });

//        final DatabaseHelper db = new DatabaseHelper(DetailContact.this);
//        contacts = db.getContacts();
        contact = contacts.get(x-1);
        name.setText(contact.getFname()+" "+contact.getLname());
        numb1.setText(contact.getNum1());
        String im = contact.getImage();
        if (im == null){
            img.setImageResource(R.drawable.person);
        }
        else {
            Uri uri = Uri.parse(im);
            img.setImageURI(null);
            img.setImageURI(uri);
        }
        if (contact.getNum2().isEmpty()){
            call.setVisibility(View.GONE);
        }
        else {
            numb2.setText(contact.getNum2());
        }
        msg1.setText(contact.getNum1());
        if (contact.getNum2().isEmpty()){
            msg.setVisibility(View.GONE);
        }
        else {
            msg2.setText(contact.getNum2());
        }
        if (contact.getEmail().isEmpty()){
            lMail.setVisibility(View.GONE);
        }
        else {
            mail.setText(contact.getEmail());
        }
        cate.setText(contact.getCategory());

        final boolean fa = contact.isFavorite();
        if (fa == true){
            fav.setImageResource(R.drawable.favorite_red);
        }
        else {
            fav.setImageResource(R.drawable.favorite);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact.isFavorite() == true){
                    fav.setImageResource(R.drawable.favorite);
                    contact.setFavorite(false);
//                    db.updateContact(contact);
                    cvm.update(contact);
                }
                else {
                    fav.setImageResource(R.drawable.favorite_red);
                    contact.setFavorite(true);
//                    db.updateContact(contact);
                    cvm.update(contact);

                }
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit : Intent inte = new Intent(DetailContact.this,ContactAdd.class);
                    inte.putExtra("con",contact.getId());
                    inte.putExtra("edit",1);
                    startActivity(inte);
                    break;

                }
                return false;
            }
        });
        final String n1 = contact.getNum1();
        final String n2 = contact.getNum2();
        final String em = contact.getEmail();
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calln1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+n1));
                startActivity(calln1);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calln2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+n2));
                startActivity(calln2);
            }
        });
        msgL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ms = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+n1));
                startActivity(ms);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ms = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+n2));
                startActivity(ms);
            }
        });
        lMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mai = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+em));
                startActivity(mai);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        DatabaseHelper databaseHelper = new DatabaseHelper(DetailContact.this);
//        boolean b = databaseHelper.updateContact(contact);
//        if (b){
//            Toast.makeText(this, "Favorite was set", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "Favorite was not able to set", Toast.LENGTH_SHORT).show();
//        }
        finish();
    }
}