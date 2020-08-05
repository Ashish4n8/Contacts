package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context,ArrayList<Contact> list){
        contacts = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv =itemView.findViewById(R.id.tvN);

        }
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tv.setText(contacts.get(position).getFname());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
