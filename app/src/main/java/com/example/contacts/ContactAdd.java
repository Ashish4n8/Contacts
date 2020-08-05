package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactAdd extends AppCompatActivity {
    Spinner cat;
    ImageView img;
    EditText fName,lName,num1,num2,email;
    String option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        cat = findViewById(R.id.dropdown);
        img = findViewById(R.id.img);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        email = findViewById(R.id.email);
        Toolbar toolbar = findViewById(R.id.tool);
        toolbar.inflateMenu(R.menu.new_contact);

        String[] options = getResources().getStringArray(R.array.options);
        ArrayAdapter ada = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(ada);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.save){
                    if (fName.getText().toString().isEmpty() || lName.getText().toString().isEmpty() || num1.getText().toString().isEmpty()){
                        Toast.makeText(ContactAdd.this, "Enter required fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Contact contact;
                        contact = new Contact(-1,fName.getText().toString().trim(),lName.getText().toString().trim(),num1.getText().toString().trim(),
                                num2.getText().toString().trim(),email.getText().toString().trim(),option,false);
                        DatabaseHelper databaseHelper = new DatabaseHelper(ContactAdd.this);
                        boolean d = databaseHelper.addContact(contact);
                        if (d){
                            Toast.makeText(ContactAdd.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(ContactAdd.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(ContactAdd.this, "Failed to add", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return false;
            }
        });

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                option = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ContactAdd.this, option+" is Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}