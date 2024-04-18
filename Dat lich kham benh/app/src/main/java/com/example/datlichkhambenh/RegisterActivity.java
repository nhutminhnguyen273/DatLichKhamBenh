package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datlichkhambenh.dao.DoctorFireBaseDAO;
import com.example.datlichkhambenh.dao.UserFireBaseDAO;
import com.example.datlichkhambenh.model.Doctor;
import com.example.datlichkhambenh.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister, btnCancel;
    RadioGroup rgGender;
    RadioButton rdbMale, rdbFemale;
    EditText txtFullName, txtDateOfBirth, txtPhoneNumber, txtEmail, txtPassword, txtConfirmPass;
    private FirebaseAuth auth;
    private UserFireBaseDAO userFireBaseDAO;
    private DoctorFireBaseDAO doctorFireBaseDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnRRegister);
        btnCancel = findViewById(R.id.btnRCancel);
        rgGender = findViewById(R.id.rgGender);
        rdbMale = findViewById(R.id.rdbMale);
        rdbFemale = findViewById(R.id.rdbFemale);
        txtFullName = findViewById(R.id.txtRFullName);
        txtDateOfBirth = findViewById(R.id.txtRDateOfBirth);
        txtPhoneNumber = findViewById(R.id.txtRPhoneNumber);
        txtEmail = findViewById(R.id.txtREmail);
        txtPassword = findViewById(R.id.txtRPassword);
        txtConfirmPass = findViewById(R.id.txtRConfirmPassword);
        btnRegister.setOnClickListener(v -> register());
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void register(){
        String fullName, dateOfBirth, phoneNumber, email, password, confirmPass;
        boolean gender;
        fullName = txtFullName.getText().toString();
        dateOfBirth = txtDateOfBirth.getText().toString().trim();
        phoneNumber = txtPhoneNumber.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();
        confirmPass = txtConfirmPass.getText().toString().trim();
        int selectedId = rgGender.getCheckedRadioButtonId();
        if(selectedId == R.id.rdbMale){
            gender = true;
        }
        else{
            gender = false;
        }
        if (fullName.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPass)) {
            Toast.makeText(this, "Mật khẩu và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                    if (email.contains("doctor")) {
                        Doctor newDoctor = new Doctor();
                        newDoctor.setFullName(fullName);
                        newDoctor.setDateOfBirth(dateOfBirth);
                        newDoctor.setPhoneNumber(phoneNumber);
                        newDoctor.setEmail(email);
                        newDoctor.setGender(gender);
                        newDoctor.setRole("Doctor");

                        doctorFireBaseDAO = new DoctorFireBaseDAO(RegisterActivity.this);
                        doctorFireBaseDAO.insert(newDoctor);
                    }
                    else{
                        User newUser = new User();
                        newUser.setFullName(fullName);
                        newUser.setDateOfBirth(dateOfBirth);
                        newUser.setPhoneNumber(phoneNumber);
                        newUser.setEmail(email);
                        newUser.setGender(gender);

                        userFireBaseDAO = new UserFireBaseDAO(RegisterActivity.this);
                        userFireBaseDAO.insert(newUser);
                    }
                    Toast.makeText(getApplicationContext(), "Registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void cancel(){
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }
}