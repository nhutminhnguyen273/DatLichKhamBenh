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

import com.example.datlichkhambenh.adapter.EvaluateAdapter;
import com.example.datlichkhambenh.dao.EvaluateFireBaseDAO;

public class ListEvaluateActivity extends AppCompatActivity {
    private EvaluateAdapter adapter;
    private EvaluateFireBaseDAO evaluateFireBaseDAO;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_evaluate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.rvLEvaluate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        evaluateFireBaseDAO = new EvaluateFireBaseDAO(this);
        evaluateFireBaseDAO.getAllEvaluate(recyclerView);
        btnCancel = findViewById(R.id.btnLECancel);
        btnCancel.setOnClickListener(v -> cancel());

    }
    private void cancel(){
        Intent i = new Intent(ListEvaluateActivity.this, DoctorActivity.class);
        startActivity(i);
    }
}