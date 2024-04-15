package com.example.datlichkhambenh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.dao.MedicalHistoryFirebaseDAO;
import com.example.datlichkhambenh.model.MedicalHistory;

import java.util.List;

public class MedicalHistoryAdapter extends RecyclerView.Adapter<MedicalHistoryAdapter.ViewHolder> {
    private List<MedicalHistory> medicalHistoryList;
    private Context context;
    private MedicalHistoryFirebaseDAO medicalHistoryFirebaseDAO;

    public MedicalHistoryAdapter(Context context, MedicalHistoryFirebaseDAO medicalHistoryFirebaseDAO, List<MedicalHistory> medicalHistoryList) {
        this.context = context;
        this.medicalHistoryList = medicalHistoryList;
        this.medicalHistoryFirebaseDAO = medicalHistoryFirebaseDAO;
    }

    @NonNull
    @Override
    public MedicalHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View medicalHistoryView = inflater.inflate(R.layout.activity_medical_history, parent, false);
        return new ViewHolder(medicalHistoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalHistoryAdapter.ViewHolder holder, int position) {
        MedicalHistory medicalHistory = medicalHistoryList.get(position);
        holder.emailTextView.setText(medicalHistory.getEmail());
        holder.medicalRecordTextView.setText(medicalHistory.getMedicalRecord());
        holder.servicesTextView.setText(medicalHistory.getServices());
        holder.prescriptionTextView.setText(medicalHistory.getPrescription());
        holder.visitDateTextView.setText(medicalHistory.getVisitDate());
        holder.paymentTextView.setText(medicalHistory.getPayment());

        // Xử lý sự kiện click item nếu cần
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng click vào một mục trong danh sách
                // Ví dụ: mở một Activity mới hoặc thực hiện hành động khác
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicalHistoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView emailTextView, medicalRecordTextView, servicesTextView, prescriptionTextView, visitDateTextView, paymentTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailEditText);
            medicalRecordTextView = itemView.findViewById(R.id.medicalRecordEditText);
            servicesTextView = itemView.findViewById(R.id.servicesEditText);
            prescriptionTextView = itemView.findViewById(R.id.prescriptionEditText);
            visitDateTextView = itemView.findViewById(R.id.visitDateEditText);
            paymentTextView = itemView.findViewById(R.id.paymentEditText);
        }
    }
}
