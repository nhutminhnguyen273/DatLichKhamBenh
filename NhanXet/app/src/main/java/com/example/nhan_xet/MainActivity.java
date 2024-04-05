package com.example.nhan_xet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextYkien;
    Button btnHuy,btnLuu;
    RadioGroup Idgroup1,Idgroup2,Idgroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Ánh xạ id
        editTextYkien=findViewById(R.id.editTextYkien);
        btnHuy=findViewById(R.id.btnHuy);
        btnLuu=findViewById(R.id.btnLuu);
        Idgroup1=findViewById(R.id.Idgroup1);
        Idgroup2=findViewById(R.id.Idgroup2);
        Idgroup3=findViewById(R.id.Idgroup3);
        // Xử lý click btnHuy và btnLuu
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đánh giá bác sĩ khám
                int idselect = Idgroup1.getCheckedRadioButtonId();
                RadioButton radselect = findViewById(idselect);
                String BacSi=radselect.getText().toString();
                // Đánh giá độ hiệu quả
                int idselect2 = Idgroup2.getCheckedRadioButtonId();
                RadioButton radselect2 = findViewById(idselect2);
                String HieuQua=radselect2.getText().toString();
                // Đánh giá chất lương dịch vụ
                int idselect3 = Idgroup3.getCheckedRadioButtonId();
                RadioButton radselect3 = findViewById(idselect3);
                String DichVu=radselect3.getText().toString();
                // Lấy ý kiến khác
                String bosung=editTextYkien.getText().toString();
                String tonghop=BacSi +"\n"+HieuQua+"\n"+DichVu+"\n";
                        tonghop +="-------Ý kiến khác-------\n";
                        tonghop+=bosung+"\n";
                        tonghop+="------------------------------------";
                // Tạo dialog hiện thị tất cả thông tin trên
                AlertDialog.Builder mydialog =new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Kết quả đánh giá");
                mydialog.setMessage(tonghop);
                mydialog.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mydialog.create().show();
            }
        });
    }

}