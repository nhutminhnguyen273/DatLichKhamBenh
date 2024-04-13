package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;
import com.example.datlichkhambenh.model.Profile;

public class ProfileActivity extends AppCompatActivity {

    Button btnCreate, btnCancel;
    RadioGroup rgGender;
    RadioButton rdbMale, rdbFemale;
    EditText txtFullName, txtDateOfBirth, txtPhoneNumber, txtEmail, txtAddress, txtCountry;
    private ProfileFireBaseDAO profileFireBaseDAO;
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
        btnCreate.setOnClickListener(v -> create());
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void create(){
        String fullName, dateOfBirth, phoneNumber, email, address, country;
        boolean gender;
        fullName = txtFullName.getText().toString();
        dateOfBirth = txtDateOfBirth.getText().toString().trim();
        phoneNumber = txtPhoneNumber.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        address = txtAddress.getText().toString();
        country = txtCountry.getText().toString();
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