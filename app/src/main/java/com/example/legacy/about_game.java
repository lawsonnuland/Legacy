package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class about_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_game);
    }

    public void returntoMainMenu(View view) {
        Intent returnFromAbout = new Intent(this, MainActivity.class);
        startActivity(returnFromAbout);
    }
}
