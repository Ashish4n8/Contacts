package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        String options[] = getResources().getStringArray(R.array.options);
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
                        Toast.makeText(ContactAdd.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                option = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ContactAdd.this, option, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}