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

    public interface ContactClicked{
        void onClicked(int index);
    }

    ContactClicked activity;
    public ContactAdapter(Context context, ArrayList<Contact> list){
        contacts = list;
        activity = (ContactClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv =itemView.findViewById(R.id.tvN);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onClicked(contacts.indexOf((Contact)view.getTag()));
                }
            });

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
        holder.itemView.setTag(contacts.get(position));
        holder.tv.setText(contacts.get(position).getFname());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
