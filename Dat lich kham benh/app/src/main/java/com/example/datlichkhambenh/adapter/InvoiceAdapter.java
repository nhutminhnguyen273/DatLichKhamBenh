package com.example.datlichkhambenh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.dao.InvoiceFireBaseDAO;
import com.example.datlichkhambenh.model.Information;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {
    private List<Information> listInformation;
    private InvoiceFireBaseDAO invoiceFireBaseDAO;
    private Context context;
    public InvoiceAdapter(Context context, InvoiceFireBaseDAO invoiceFireBaseDAO, List<Information> listInformation){
        this.context = context;
        this.invoiceFireBaseDAO = invoiceFireBaseDAO;
        this.listInformation = listInformation;
    }
    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View informationView = inflater.inflate(R.layout.item_invoice, parent, false);
        return new ViewHolder(informationView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder holder, int position) {
        Information temp = listInformation.get(position);
        holder.txtFullName.setText("Họ và tên: "+temp.getFullName());
        holder.txtDateOfBirth.setText("Ngày sinh: "+temp.getDateOfBirth());
        if(temp.isGender() == true){
            holder.txtGender.setText("Nam");
        }
        else{
            holder.txtGender.setText("Nữ");
        }
        holder.txtPhoneNumber.setText("Số điện thoại: "+temp.getPhoneNumber());
        holder.txtEmail.setText("Email: "+temp.getEmail());
        holder.txtDoctor.setText("Bác sĩ: "+temp.getDoctor());
        holder.txtSick.setText("Bệnh: "+temp.getSick());
        holder.txtDate.setText("Ngày khám: "+temp.getDate());
        holder.txtTotalAmount.setText("Tổng tiền: "+temp.getTotalAmount());
    }
    @Override
    public int getItemCount() {
        return listInformation.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtDateOfBirth, txtGender, txtPhoneNumber, txtEmail, txtDoctor, txtSick, txtDate, txtTotalAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = (TextView) itemView.findViewById(R.id.txtIIFullName);
            txtDateOfBirth = (TextView) itemView.findViewById(R.id.txtIIDateOfBirth);
            txtGender = (TextView) itemView.findViewById(R.id.txtIIGender);
            txtPhoneNumber = (TextView) itemView.findViewById(R.id.txtIIPhoneNumber);
            txtEmail = (TextView) itemView.findViewById(R.id.txtIIEmail);
            txtDoctor = (TextView) itemView.findViewById(R.id.txtIIDoctor);
            txtSick = (TextView) itemView.findViewById(R.id.txtIISick);
            txtDate = (TextView) itemView.findViewById(R.id.txtIIDate);
            txtTotalAmount = (TextView) itemView.findViewById(R.id.txtIITotalAmount);
        }
    }
}
