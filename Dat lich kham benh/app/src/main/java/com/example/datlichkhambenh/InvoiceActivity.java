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

import com.example.datlichkhambenh.dao.InvoiceFireBaseDAO;

public class InvoiceActivity extends AppCompatActivity {
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_invoice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InvoiceFireBaseDAO invoiceBaseDAO = new InvoiceFireBaseDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rvInvoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        invoiceBaseDAO.getInvoiceByEmail(recyclerView);
        btnCancel = findViewById(R.id.btnInvoiceCancel);
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void cancel(){
        Intent i = new Intent(InvoiceActivity.this, MainActivity.class);
        startActivity(i);
    }
}