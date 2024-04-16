package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Adapter.EmailAdapter;
import com.example.finalproject.Models.Email;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResponseActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Button btnAdd;
    private TextView edtId;
    private EditText edtTitle,edtDesc;
    private List<Email> mListEmail;

    private EmailAdapter memailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String title = edtTitle.getText().toString().trim();
                String desc = edtDesc.getText().toString().trim();
                Email email = new Email(id,title,desc);
                addEmail(email);
            }
        });
        getDataFromFirebase();
    }
    private void init(){
        edtId = findViewById(R.id.txbId);
        edtTitle = findViewById(R.id.txbTitle);
        edtDesc = findViewById(R.id.txbDesc);
        btnAdd = findViewById(R.id.btnAdd);
        mListEmail = new ArrayList<>();
        memailAdapter = new EmailAdapter(mListEmail);
    }
    private void addEmail (Email email){
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("emails");
        String path = String.valueOf(email.getEmailID());
        ref.child(path).setValue(email, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ResponseActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataFromFirebase (){
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("emails");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(mListEmail != null){
                    mListEmail.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Email email = dataSnapshot.getValue(Email.class);
                    mListEmail.add(email);
                }
                memailAdapter.notifyDataSetChanged();
                Email lastEmail = mListEmail.get(mListEmail.size() - 1);
                String lastEmailID = lastEmail.getEmailID();
                String newEmailID = incrementEmailID(lastEmailID);
                edtId.setText(newEmailID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ResponseActivity.this,"Loi",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String incrementEmailID(String emailIDString) {
        try {
            int emailIDInt = Integer.parseInt(emailIDString);
            emailIDInt++;
            return String.valueOf(emailIDInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}