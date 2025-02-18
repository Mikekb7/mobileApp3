package com.example.mobileapp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter {
    private ArrayList<String> contactData;
    private View.OnClickListener mOnItemClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewContact;

        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName);/*This is assigning from the item to the textview, it shows whats going to be in the cell*/
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }                                                                 /*This is a container that holds a view( a single list item)*/

        public TextView getContactTextView(){
            return textViewContact;
        }
    }

    public ContactAdapter(ArrayList<String> arrayList){
        contactData = arrayList;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view,parent,false); //onCreateViewHolder() is responsible for creating the visual structure of a single item in the RecyclerView
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getContactTextView().setText(contactData.get(position));      // Binding data to view
    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }
}
