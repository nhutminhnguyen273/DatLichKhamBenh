package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datlichkhambenh.dao.InvoiceFireBaseDAO;
import com.example.datlichkhambenh.model.Information;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EnterInformationActivity extends AppCompatActivity {
    EditText txtFullName, txtDateOfBirth, txtPhoneNumber, txtEmail, txtSick, txtDate, txtTotal;
    Spinner spDoctor;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    Button btnCancel, btnAdd;
    private FirebaseFirestore db;
    private InvoiceFireBaseDAO invoiceFireBaseDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        btnCancel = findViewById(R.id.btnEICancel);
        btnAdd = findViewById(R.id.btnEIAdd);
        txtFullName = findViewById(R.id.txtEIFullName);
        txtDateOfBirth = findViewById(R.id.txtEIDateOfBirth);
        txtPhoneNumber = findViewById(R.id.txtEIPhoneNumber);
        txtEmail = findViewById(R.id.txtEIEmail);
        txtSick = findViewById(R.id.txtEISick);
        txtDate = findViewById(R.id.txtEIDate);
        txtTotal = findViewById(R.id.txtEITotalAmount);
        spDoctor = findViewById(R.id.spEIDoctor);
        rgGender = findViewById(R.id.rgEIGender);
        rbMale = findViewById(R.id.rdbEIMale);
        rbFemale = findViewById(R.id.rdbEIFemale);
        loadDoctors();
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
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(EnterInformationActivity.this,
                                    android.R.layout.simple_spinner_item, doctorsList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spDoctor.setAdapter(adapter);
                        } else {
                            Toast.makeText(EnterInformationActivity.this, "Lỗi khi tải danh sách bác sĩ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void add(){
        String fullName, dateOfBirth, phoneNumber, email, doctor, sick, date;
        int totalAmount;
        boolean gender;
        fullName = txtFullName.getText().toString();
        dateOfBirth = txtDateOfBirth.getText().toString().trim();
        phoneNumber = txtPhoneNumber.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        doctor = spDoctor.getSelectedItem().toString();
        sick = txtSick.getText().toString();
        date = txtDate.getText().toString().trim();
        totalAmount = Integer.parseInt(txtTotal.getText().toString());
        int selectedId = rgGender.getCheckedRadioButtonId();
        if(selectedId == R.id.rdbMale){
            gender = true;
        }
        else{
            gender = false;
        }
        if (fullName.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || doctor.isEmpty() || sick.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        Information newInformation = new Information();
        newInformation.setFullName(fullName);
        newInformation.setDateOfBirth(dateOfBirth);
        newInformation.setPhoneNumber(phoneNumber);
        newInformation.setEmail(email);
        newInformation.setGender(gender);
        newInformation.setSick(sick);
        newInformation.setDate(date);
        newInformation.setDoctor(doctor);
        newInformation.setTotalAmount(totalAmount);
        invoiceFireBaseDAO = new InvoiceFireBaseDAO(EnterInformationActivity.this);
        invoiceFireBaseDAO.insert(newInformation);
        Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EnterInformationActivity.this, DoctorActivity.class);
        startActivity(i);
    }
    private void cancel(){
        Intent i = new Intent(EnterInformationActivity.this, DoctorActivity.class);
        startActivity(i);
    }
}