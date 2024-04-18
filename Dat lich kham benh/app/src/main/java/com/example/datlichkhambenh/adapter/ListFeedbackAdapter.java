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
import com.example.datlichkhambenh.dao.FeedbackFireBaseDAO;
import com.example.datlichkhambenh.model.Feedback;

import java.util.List;

public class ListFeedbackAdapter extends RecyclerView.Adapter<ListFeedbackAdapter.ViewHolder> {
    private List<Feedback> listFeedback;
    private FeedbackFireBaseDAO feedbackFireBaseDAO;
    private Context context;
    public ListFeedbackAdapter(Context context, FeedbackFireBaseDAO feedbackFireBaseDAO, List<Feedback> listFeedback){
        this.context = context;
        this.listFeedback = listFeedback;
        this.feedbackFireBaseDAO = feedbackFireBaseDAO;
    }
    @NonNull
    @Override
    public ListFeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View feedbackView = inflater.inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(feedbackView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListFeedbackAdapter.ViewHolder holder, int position) {
        Feedback temp = listFeedback.get(position);
        holder.txtFullName.setText("Họ và tên: "+temp.getFullName());
        holder.txtEmail.setText("Email: "+temp.getEmailSent());
        holder.txtEmailDoctor.setText("Email bác sĩ: "+temp.getEmailDoctor());
        holder.txtDoctor.setText("Bác sĩ: "+temp.getDoctor());
        holder.txtContent.setText("Nội dung: "+temp.getContent());
    }

    @Override
    public int getItemCount() {
        return listFeedback.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtEmail, txtDoctor, txtEmailDoctor, txtContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.txtLFFullName);
            txtEmail = itemView.findViewById(R.id.txtLFEmail);
            txtEmailDoctor = itemView.findViewById(R.id.txtLFEmailDoctor);
            txtDoctor = itemView.findViewById(R.id.txtLFDoctor);
            txtContent = itemView.findViewById(R.id.txtLFContent);
        }
    }
}
