package com.example.contacts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Group extends Fragment implements ContactAdapter.ContactClicked {
    private int cat;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    View view;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Contact> contacts;
    ArrayList<Contact> friends;
    ArrayList<Contact> family;

    public Group(int category) {
        this.cat = category;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.groups,container,false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.side_menus);
        drawer = ((MainActivity)getActivity()).findViewById(R.id.drawable_layout);
        toggle = new ActionBarDrawerToggle(getActivity(), drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (cat==0){
            toolbar.setTitle("Family");
        }
        else {
            toolbar.setTitle("Friends");
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search :
                        Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ie:
                        Toast.makeText(getActivity(), "Import/Export", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sortN:
                        Toast.makeText(getActivity(), "Sort by first name", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sortL:
                        Toast.makeText(getActivity(), "Sort by last name", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        DatabaseHelper db = new DatabaseHelper(this.getActivity());
        contacts = db.getContacts();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        friends = new ArrayList<Contact>();
        family = new ArrayList<Contact>();
        for (int i=0;i<contacts.size();i++){
            Contact test = contacts.get(i);
            if (test.getCategory().equals("Family")){
                family.add(test);

            }
            else {
                friends.add(test);
            }
        }

        if (cat == 0){
            myAdapter = new ContactAdapter(family,this);
        }
        else {
            myAdapter = new ContactAdapter(friends, this);
        }
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClicked(int index) {

    }
}
