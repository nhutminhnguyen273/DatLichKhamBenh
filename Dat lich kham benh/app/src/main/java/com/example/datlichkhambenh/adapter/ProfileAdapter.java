package com.example.datlichkhambenh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.CreateMedicalExaminationFormActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;
import com.example.datlichkhambenh.model.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<Profile> listProfile;
    private ProfileFireBaseDAO profileFireBaseDAO;
    private Context context;
    public ProfileAdapter(Context context, ProfileFireBaseDAO profileFireBaseDAO, List<Profile> listProfile){
        this.context = context;
        this.listProfile = listProfile;
        this.profileFireBaseDAO = profileFireBaseDAO;
    }
    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View profileView = inflater.inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(profileView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Profile temp = listProfile.get(position);
        holder.txtFullName.setText(temp.getFullName());
        holder.txtDateOfBirth.setText(temp.getDateOfBirth());
        if(temp.getGender()){
            holder.txtGender.setText("Nam");
        }
        else{
            holder.txtGender.setText("Nữ");
        }
        holder.txtAddress.setText(temp.getAddress());
        holder.txtCountry.setText(temp.getCountry());
        holder.txtPhoneNumber.setText(temp.getPhoneNumber());
        holder.txtEmail.setText(temp.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CreateMedicalExaminationFormActivity.class);
                i.putExtra("id", temp.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtDateOfBirth, txtGender, txtAddress, txtCountry, txtPhoneNumber, txtEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = (TextView) itemView.findViewById(R.id.txtLPFullName);
            txtDateOfBirth = (TextView) itemView.findViewById(R.id.txtLPDateOfBirth);
            txtGender = (TextView) itemView.findViewById(R.id.txtLPGender);
            txtAddress = (TextView) itemView.findViewById(R.id.txtLPAddress);
            txtCountry = (TextView) itemView.findViewById(R.id.txtLPCountry);
            txtPhoneNumber = (TextView) itemView.findViewById(R.id.txtLPPhoneNumber);
            txtEmail = (TextView) itemView.findViewById(R.id.txtLPEmail);
        }
    }
}