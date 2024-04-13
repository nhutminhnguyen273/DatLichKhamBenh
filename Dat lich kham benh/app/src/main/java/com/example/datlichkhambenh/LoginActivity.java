package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText txtEmail, txtPassword;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnLLogin);
        btnRegister = findViewById(R.id.btnLRegister);
        txtEmail = findViewById(R.id.txtLEmail);
        txtPassword = findViewById(R.id.txtLPass);
        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> register());
    }
    private void login(){
        String email, password;
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void register(){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}