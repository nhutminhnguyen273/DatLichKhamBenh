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
        holder.txtFullName.setText("Họ tên: "+temp.getFullName());
        holder.txtDateOfBirth.setText("Ngày sinh: "+temp.getDateOfBirth());
        if(temp.isGender() == true){
            holder.txtGender.setText("Nam");
        }
        else{
            holder.txtGender.setText("Nữ");
        }
        holder.txtAddress.setText("Địa chỉ: "+temp.getAddress());
        holder.txtCountry.setText("Quốc gia: "+temp.getCountry());
        holder.txtPhoneNumber.setText("SĐT: "+temp.getPhoneNumber());
        holder.txtEmail.setText("Email: "+temp.getEmail());
        holder.txtSick.setText("Chịu chứng: "+temp.getSick());
        holder.txtDoctor.setText("Bác sĩ: "+temp.getDoctor());
        holder.txtMEDate.setText("Ngày khám: "+temp.getMEDate());
        if(temp.isStatus() == false){
            holder.txtStatus.setText("Chờ xử lý");
        }
        else{
            holder.txtStatus.setText("Xác nhận");
        }
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtDateOfBirth, txtGender, txtAddress, txtCountry, txtPhoneNumber, txtEmail, txtSick, txtDoctor, txtMEDate, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = (TextView) itemView.findViewById(R.id.txtLPFullName);
            txtDateOfBirth = (TextView) itemView.findViewById(R.id.txtLPDateOfBirth);
            txtGender = (TextView) itemView.findViewById(R.id.txtLPGender);
            txtAddress = (TextView) itemView.findViewById(R.id.txtLPAddress);
            txtCountry = (TextView) itemView.findViewById(R.id.txtLPCountry);
            txtPhoneNumber = (TextView) itemView.findViewById(R.id.txtLPPhoneNumber);
            txtEmail = (TextView) itemView.findViewById(R.id.txtLPEmail);
            txtSick = (TextView) itemView.findViewById(R.id.txtLPSick);
            txtDoctor = (TextView) itemView.findViewById(R.id.txtLPDoctor);
            txtMEDate = (TextView) itemView.findViewById(R.id.txtLPMEDate);
            txtStatus = (TextView) itemView.findViewById(R.id.txtLPStatus);
        }
    }
}