package com.example.datlichkhambenh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;
import com.example.datlichkhambenh.model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    Button btnCreate, btnCancel;
    RadioGroup rgGender;
    RadioButton rdbMale, rdbFemale;
    Spinner spDoctor;
    TextView txtStatus;
    EditText txtFullName, txtDateOfBirth, txtPhoneNumber, txtEmail, txtAddress, txtCountry, txtSick, txtMEDate;
    private ProfileFireBaseDAO profileFireBaseDAO;
    private FirebaseFirestore db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCreate = findViewById(R.id.btnPCreate);
        btnCancel = findViewById(R.id.btnPCancel);
        rgGender = findViewById(R.id.rgPGender);
        rdbMale = findViewById(R.id.rdbPMale);
        rdbFemale = findViewById(R.id.rdbPFemale);
        txtFullName = findViewById(R.id.txtPFullName);
        txtDateOfBirth = findViewById(R.id.txtPDateOfBirth);
        txtPhoneNumber = findViewById(R.id.txtPPhoneNumber);
        txtEmail = findViewById(R.id.txtPEmail);
        txtAddress = findViewById(R.id.txtPAddress);
        txtCountry = findViewById(R.id.txtPCountry);
        txtSick = findViewById(R.id.txtPSick);
        spDoctor = findViewById(R.id.spPDoctor);
        txtMEDate = findViewById(R.id.txtPMEDate);
        txtStatus = findViewById(R.id.txtPStatus);
        db = FirebaseFirestore.getInstance();
        loadDoctors();
        btnCreate.setOnClickListener(v -> create());
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
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ProfileActivity.this,
                                    android.R.layout.simple_spinner_item, doctorsList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spDoctor.setAdapter(adapter);
                        } else {
                            Toast.makeText(ProfileActivity.this, "Lỗi khi tải danh sách bác sĩ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void create(){
        String fullName, dateOfBirth, phoneNumber, email, address, country, sick, mEDate, doctor;
        boolean gender, status;
        fullName = txtFullName.getText().toString();
        dateOfBirth = txtDateOfBirth.getText().toString().trim();
        phoneNumber = txtPhoneNumber.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        address = txtAddress.getText().toString();
        country = txtCountry.getText().toString();
        sick = txtSick.getText().toString();
        mEDate = txtMEDate.getText().toString().trim();
        if(txtStatus.getText().toString().equals("Chờ xử lý")){
            status = false;
        }
        else{
            status = true;
        }
        doctor = spDoctor.getSelectedItem().toString();
        int selectedId = rgGender.getCheckedRadioButtonId();
        if(selectedId == R.id.rdbMale){
            gender = true;
        }
        else{
            gender = false;
        }
        if (fullName.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        Profile newProfile = new Profile();
        newProfile.setFullName(fullName);
        newProfile.setDateOfBirth(dateOfBirth);
        newProfile.setPhoneNumber(phoneNumber);
        newProfile.setEmail(email);
        newProfile.setAddress(address);
        newProfile.setCountry(country);
        newProfile.setGender(gender);
        newProfile.setSick(sick);
        newProfile.setMEDate(mEDate);
        newProfile.setStatus(status);
        newProfile.setDoctor(doctor);
        profileFireBaseDAO = new ProfileFireBaseDAO(ProfileActivity.this);
        profileFireBaseDAO.insert(newProfile);

        Toast.makeText(getApplicationContext(), "Tạo hồ sơ thành công!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(i);
    }
    private void cancel(){
        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(i);
    }
}