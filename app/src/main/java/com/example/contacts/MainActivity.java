package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    //ActionBarDrawerToggle toggle;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        drawer = findViewById(R.id.drawable_layout);
        nav = findViewById(R.id.nav);
        //toggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.open, R.string.close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Home()).commit();
        nav.setCheckedItem(R.id.contact);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.contact :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Home()).commit();
                        break;
                    case R.id.fav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Favorite()).commit();
                        break;
                    case R.id.settings:

                        break;
                    case R.id.family:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Group(0)).commit();
                        break;
                    case R.id.friend:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Group(1)).commit();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactAdd.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}