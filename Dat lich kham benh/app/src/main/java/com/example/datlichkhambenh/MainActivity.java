package com.example.datlichkhambenh;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datlichkhambenh.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button btnCreateProfile, btnFeedback, btnHistory, btnListProfile, btnListFeedback, btnEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.datlichkhambenh.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        btnCreateProfile = findViewById(R.id.btnHCreateProfile);
        btnHistory = findViewById(R.id.btnHHistory);
        btnListProfile = findViewById(R.id.btnHListProfile);
        btnFeedback = findViewById(R.id.btnHFeedback);
        btnListFeedback = findViewById(R.id.btnHListFeedback);
        btnEvaluate = findViewById(R.id.btnHEvaluate);
        btnCreateProfile.setOnClickListener(v -> createProfile());
        btnListProfile.setOnClickListener(v -> listProfile());
        btnHistory.setOnClickListener(v -> history());
        btnFeedback.setOnClickListener(v -> feedback());
        btnListFeedback.setOnClickListener(v -> listFeedback());
        btnEvaluate.setOnClickListener(v -> evaluate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void createProfile(){
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }
    private void listProfile(){
        Intent i = new Intent(MainActivity.this, ListProfileActivity.class);
        startActivity(i);
    }
    private void history(){
        Intent i = new Intent(MainActivity.this, InvoiceActivity.class);
        startActivity(i);
    }
    private void feedback(){
        Intent i = new Intent(MainActivity.this, FeedbackActivity.class);
        startActivity(i);
    }
    private void listFeedback(){
        Intent i = new Intent(MainActivity.this, ListFeedbackActivity.class);
        startActivity(i);
    }
    private void evaluate(){
        Intent i = new Intent(MainActivity.this, EvaluateActivity.class);
        startActivity(i);
    }
}