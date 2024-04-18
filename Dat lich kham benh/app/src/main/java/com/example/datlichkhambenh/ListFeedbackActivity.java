package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.dao.FeedbackFireBaseDAO;

public class ListFeedbackActivity extends AppCompatActivity {
    Button btnCancel;
    private FeedbackFireBaseDAO feedbackFireBaseDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        feedbackFireBaseDAO = new FeedbackFireBaseDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rvLFeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackFireBaseDAO.getFeedbackByEmailSent(recyclerView);
        btnCancel = findViewById(R.id.btnLFCancel);
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void cancel(){
        Intent i = new Intent(ListFeedbackActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}