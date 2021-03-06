package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends Fragment implements ContactAdapter.ContactClicked {
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    View view;
    RecyclerView recyclerView;
    //RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Contact> contacts;
    List<Contact> fav;
    ContactViewModel cvm;
    ContactAdapter myAdapter = new ContactAdapter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.side_menus);
        drawer = ((MainActivity)getActivity()).findViewById(R.id.drawable_layout);
        toggle = new ActionBarDrawerToggle((MainActivity)getActivity(), drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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

        fav = new ArrayList<Contact>();
        cvm =new ViewModelProvider(this).get(ContactViewModel.class);
        cvm.getall().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> list) {
                contacts = list;
                for (int i=0;i<contacts.size();i++){
                    Contact test = contacts.get(i);
                    if (test.isFavorite()){
                        fav.add(test);
                    }
                }
                myAdapter.setcons(fav);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.listo);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClicked(int index) {
        Intent intent = new Intent(this.getActivity(),DetailContact.class);
        Contact con = contacts.get(index);
        int conId = con.getId();
        intent.putExtra("contact",conId);
        startActivity(intent);
    }
}
