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

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private RecyclerView rcvEmail;
    private EmailAdapter memailAdapter;
    private List<Email> mListEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDataFromFirebase();
    }
    private void init(){
        rcvEmail = findViewById(R.id.rcvComment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvEmail.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvEmail.addItemDecoration(dividerItemDecoration);
        mListEmail = new ArrayList<>();
        memailAdapter = new EmailAdapter(mListEmail);
        rcvEmail.setAdapter(memailAdapter);
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Loi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}