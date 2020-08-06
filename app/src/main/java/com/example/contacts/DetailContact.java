package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailContact extends AppCompatActivity {
    ImageView img;
    TextView name, numb1, numb2,msg1,msg2,mail, cate;
    LinearLayout call, msg, lMail,call1,msgL;

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


        final Contact contact = (Contact)getIntent().getSerializableExtra("contact");
        name.setText(contact.getFname()+" "+contact.getLname());
        numb1.setText(contact.getNum1());
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

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calln1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contact.getNum1()));
                startActivity(calln1);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calln2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contact.getNum2()));
                startActivity(calln2);
            }
        });
        msgL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ms = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+contact.getNum1()));
                startActivity(ms);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ms = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+contact.getNum2()));
                startActivity(ms);
            }
        });
        lMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mai = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+contact.getEmail()));
                startActivity(mai);
            }
        });
    }
}