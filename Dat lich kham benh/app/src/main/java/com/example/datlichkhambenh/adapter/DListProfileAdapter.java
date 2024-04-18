package com.example.datlichkhambenh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.DListProfileActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;
import com.example.datlichkhambenh.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class DListProfileAdapter extends RecyclerView.Adapter<DListProfileAdapter.ViewHolder> {
    private List<Profile> listProfile;
    private ProfileFireBaseDAO profileFireBaseDAO;
    private Context context;

    public DListProfileAdapter(Context context, ProfileFireBaseDAO profileFireBaseDAO, List<Profile> listProfile){
        this.context = context;
        this.listProfile = listProfile;
        this.profileFireBaseDAO = profileFireBaseDAO;
    }
    @NonNull
    @Override
    public DListProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dProfileView = inflater.inflate(R.layout.item_dprofile, parent, false);
        return new ViewHolder(dProfileView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DListProfileAdapter.ViewHolder holder, int position) {
        Profile temp = listProfile.get(position);
        holder.txtFullName.setText("Họ tên: "+temp.getFullName());
        holder.txtDateOfBirth.setText("Ngày sinh: "+temp.getDateOfBirth());
        holder.txtGender.setText(temp.isGender() ? "Nam" : "Nữ");
        holder.txtAddress.setText("Địa chỉ: "+temp.getAddress());
        holder.txtCountry.setText("Quốc gia: "+temp.getCountry());
        holder.txtPhoneNumber.setText("SĐT: "+temp.getPhoneNumber());
        holder.txtEmail.setText("Email: "+temp.getEmail());
        holder.txtSick.setText("Chịu chứng: "+temp.getSick());
        holder.txtDoctor.setText("Bác sĩ: "+temp.getDoctor());
        holder.txtMEDate.setText("Ngày khám: "+temp.getMEDate());
       if(temp.isStatus() == true){
           holder.txtStatus.setText("Xác nhận");
       }
       else{
           holder.txtStatus.setText("Chờ xử lý");
       }
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setStatus(!temp.isStatus());
                profileFireBaseDAO.updateProfileStatus(temp);
                Intent intent = new Intent(context, DListProfileActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtDateOfBirth, txtGender, txtAddress, txtCountry, txtPhoneNumber, txtEmail, txtSick, txtDoctor, txtMEDate, txtStatus;
        Button btnConfirm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.txtDLPFullName);
            txtDateOfBirth = itemView.findViewById(R.id.txtDLPDateOfBirth);
            txtGender = itemView.findViewById(R.id.txtDLPGender);
            txtAddress = itemView.findViewById(R.id.txtDLPAddress);
            txtCountry = itemView.findViewById(R.id.txtDLPCountry);
            txtPhoneNumber = itemView.findViewById(R.id.txtDLPPhoneNumber);
            txtEmail = itemView.findViewById(R.id.txtDLPEmail);
            txtSick = itemView.findViewById(R.id.txtDLPSick);
            txtDoctor = itemView.findViewById(R.id.txtDLPDoctor);
            txtMEDate = itemView.findViewById(R.id.txtDLPMEDate);
            txtStatus = itemView.findViewById(R.id.txtDLPStatus);
            btnConfirm = itemView.findViewById(R.id.btnDLPConfirm);
        }
    }
}