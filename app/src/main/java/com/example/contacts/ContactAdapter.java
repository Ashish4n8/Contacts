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
    private ContactClicked mcontactClicked;

    public interface ContactClicked{
        void onClicked(int index);
    }


    public ContactAdapter(ArrayList<Contact> list, ContactClicked contactClicked){
        contacts = list;
        this.mcontactClicked = contactClicked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ContactClicked contactClicked;
        public ViewHolder(@NonNull View itemView, final ContactClicked contactClicked) {
            super(itemView);
            tv =itemView.findViewById(R.id.tvN);
            this.contactClicked = contactClicked;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contactClicked.onClicked(contacts.indexOf((Contact)view.getTag()));
                }
            });

        }
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);

        return new ViewHolder(v,mcontactClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.tv.setText(contacts.get(position).getFname());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
