package com.example.nhan_xet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
                // Kiểm tra null cho các đối tượng đã ánh xạ
                if (editTextYkien == null || btnLuu == null || Idgroup1 == null || Idgroup2 == null || Idgroup3 == null) {
                    return;
                }

                // Đảm bảo một trong các RadioButton đã được chọn
                int idselect = Idgroup1.getCheckedRadioButtonId();
                if (idselect == -1) {
                    // Hiển thị thông báo hoặc thực hiện hành động phù hợp
                    return;
                }

                // Khởi tạo FirebaseFirestore
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Đánh giá bác sĩ khám
                RadioButton radselect = findViewById(idselect);
                String BacSi = radselect != null ? radselect.getText().toString() : "";

                // Đánh giá độ hiệu quả
                int idselect2 = Idgroup2.getCheckedRadioButtonId();
                RadioButton radselect2 = findViewById(idselect2);
                String HieuQua = radselect2 != null ? radselect2.getText().toString() : "";

                // Đánh giá chất lượng dịch vụ
                int idselect3 = Idgroup3.getCheckedRadioButtonId();
                RadioButton radselect3 = findViewById(idselect3);
                String DichVu = radselect3 != null ? radselect3.getText().toString() : "";

                // Lấy ý kiến khác
                String bosung = editTextYkien.getText().toString();

                // Xử lý trường hợp editTextYkien có thể null
                if (bosung == null) {
                    bosung = "";
                }

                // Tạo một đối tượng Map chứa dữ liệu đánh giá
                Map<String, Object> danhGia = new HashMap<>();
                danhGia.put("bacSi", BacSi);
                danhGia.put("hieuQua", HieuQua);
                danhGia.put("dichVu", DichVu);
                danhGia.put("yKienKhac", bosung);

                // Ghi dữ liệu vào Firestore
                db.collection("danh_gia")
                        .add(danhGia)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // Nếu ghi dữ liệu thành công, hiển thị thông báo hoặc thực hiện các hành động khác
                                Toast.makeText(MainActivity.this, "Đã lưu đánh giá vào Firebase Firestore", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Nếu có lỗi xảy ra khi ghi dữ liệu, hiển thị thông báo lỗi hoặc xử lý lỗi khác
                                Toast.makeText(MainActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

}