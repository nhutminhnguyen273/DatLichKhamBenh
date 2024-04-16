package com.example.finalproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Models.Email;
import com.example.finalproject.R;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> {
    private List<Email> mListEmail;

    public EmailAdapter(List<Email> mListEmail) {
        this.mListEmail = mListEmail;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email,parent,false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        Email email = mListEmail.get(position);
        if(email == null){
            return;
        }
        holder.txvTitle.setText(email.getTitle());
        holder.txvDesc.setText(email.getDescription());
    }

    @Override
    public int getItemCount() {
        if(mListEmail != null){
            return mListEmail.size();
        }
        return 0;
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder{
        private TextView txvTitle,txvDesc;
        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTitle = itemView.findViewById(R.id.txvTitle);
            txvDesc = itemView.findViewById(R.id.txvDesc);
        }
    }
}
