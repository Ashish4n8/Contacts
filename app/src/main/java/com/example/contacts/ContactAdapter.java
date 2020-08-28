package com.example.contacts;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> contacts;
    private ContactClicked mcontactClicked;

    public interface ContactClicked{
        void onClicked(int index);
    }


    public ContactAdapter(ContactClicked contactClicked){

        this.mcontactClicked = contactClicked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        ContactClicked ncontactClicked;
        public ViewHolder(@NonNull View itemView, final ContactClicked contactClicked) {
            super(itemView);
            tv =itemView.findViewById(R.id.tvN);
            img = itemView.findViewById(R.id.imaA);
            this.ncontactClicked = contactClicked;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ncontactClicked.onClicked(contacts.indexOf((Contact)view.getTag()));
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
        Contact con = contacts.get(position);
        holder.itemView.setTag(con);
        holder.tv.setText(con.getFname());
        String im = con.getImage();
        if (im == null){
            holder.img.setImageResource(R.drawable.person);
        }
        else {
            Uri uri = Uri.parse(im);
            holder.img.setImageURI(null);
            holder.img.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {

        return contacts.size();
    }

    public void setcons(List<Contact> list){
        contacts = list;
        notifyDataSetChanged();
    }
}
