package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datlichkhambenh.dao.EvaluateFireBaseDAO;
import com.example.datlichkhambenh.model.Evaluate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EvaluateActivity extends AppCompatActivity {
    Spinner spDoctor, spEvaluate;
    EditText txtEmail, txtComment, txtEvaluateDate;
    Button btnAdd, btnCancel;
    private EvaluateFireBaseDAO evaluateFireBaseDAO;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evaluate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        btnAdd = findViewById(R.id.btnEAdd);
        btnCancel = findViewById(R.id.btnECancel);
        txtEmail = findViewById(R.id.txtEEmail);
        txtComment = findViewById(R.id.txtEComment);
        txtEvaluateDate = findViewById(R.id.txtEEvaluateDate);
        spDoctor = findViewById(R.id.spEDoctor);
        spEvaluate = findViewById(R.id.spEvaluate);
        loadDoctors();
        loadEvaluationOptions();
        btnAdd.setOnClickListener(v -> add());
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void loadDoctors() {
        db.collection("Doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> doctorsList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String doctorName = document.getString("fullName");
                                doctorsList.add(doctorName);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(EvaluateActivity.this,
                                    android.R.layout.simple_spinner_item, doctorsList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spDoctor.setAdapter(adapter);
                        } else {
                            Toast.makeText(EvaluateActivity.this, "Lỗi khi tải danh sách bác sĩ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void loadEvaluationOptions() {
        List<String> evaluationOptions = new ArrayList<>();
        evaluationOptions.add("Hài lòng");
        evaluationOptions.add("Không hài lòng");
        evaluationOptions.add("Rất hài lòng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(EvaluateActivity.this,
                android.R.layout.simple_spinner_item, evaluationOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEvaluate.setAdapter(adapter);
    }
    private void add(){
        String email, doctor, evaluateDate, evaluate, comment;
        email = txtEmail.getText().toString();
        evaluateDate = txtEvaluateDate.getText().toString().trim();
        comment = txtComment.getText().toString().trim();
        doctor = spDoctor.getSelectedItem().toString();
        evaluate = spEvaluate.getSelectedItem().toString();
        Evaluate newEvaluate = new Evaluate();
        newEvaluate.setEmail(email);
        newEvaluate.setDoctor(doctor);
        newEvaluate.setEvaluateDate(evaluateDate);
        newEvaluate.setComment(comment);
        newEvaluate.setEvaluate(evaluate);
        evaluateFireBaseDAO = new EvaluateFireBaseDAO(EvaluateActivity.this);
        evaluateFireBaseDAO.insert(newEvaluate);
        Toast.makeText(getApplicationContext(), "Đánh giá thành công!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EvaluateActivity.this, MainActivity.class);
    }
    private void cancel(){
        Intent i = new Intent(EvaluateActivity.this, MainActivity.class);
        startActivity(i);
    }
}