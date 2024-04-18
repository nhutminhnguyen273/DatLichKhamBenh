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
import com.example.datlichkhambenh.dao.EvaluateFireBaseDAO;
import com.example.datlichkhambenh.model.Evaluate;
import com.example.datlichkhambenh.model.Feedback;

import java.util.List;

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {
    private List<Evaluate> listEvaluate;
    private EvaluateFireBaseDAO evaluateFireBaseDAO;
    private Context context;
    public EvaluateAdapter(Context context, EvaluateFireBaseDAO evaluateFireBaseDAO, List<Evaluate> listEvaluate){
        this.context = context;
        this.listEvaluate = listEvaluate;
        this.evaluateFireBaseDAO = evaluateFireBaseDAO;
    }
    @NonNull
    @Override
    public EvaluateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View evaluateView = inflater.inflate(R.layout.item_evaluate, parent, false);
        return new ViewHolder(evaluateView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EvaluateAdapter.ViewHolder holder, int position) {
        Evaluate temp = listEvaluate.get(position);
        holder.txtEmail.setText("Email: "+temp.getEmail());
        holder.txtDoctor.setText("Bác sĩ: "+temp.getDoctor());
        holder.txtEvaluateDate.setText("Ngày đánh giá: "+temp.getEvaluateDate());
        holder.txtEvaluate.setText("Đánh giá: "+temp.getEvaluate());
        holder.txtComment.setText("Bình luận: "+temp.getComment());
    }

    @Override
    public int getItemCount() {
        return listEvaluate.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtEmail, txtDoctor, txtEvaluateDate, txtEvaluate, txtComment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.txtLEEmail);
            txtDoctor = itemView.findViewById(R.id.txtLEDoctor);
            txtEvaluateDate = itemView.findViewById(R.id.txtLEEvaluateDate);
            txtEvaluate = itemView.findViewById(R.id.txtLEEvaluate);
            txtComment = itemView.findViewById(R.id.txtLEComment);
        }
    }
}
