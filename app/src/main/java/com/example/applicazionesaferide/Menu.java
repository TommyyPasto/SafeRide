package com.example.applicazionesaferide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnMod1 = findViewById(R.id.btnMod1);
        Button btnMod2 = findViewById(R.id.imageViewMod2);
        Button btnMod3 = findViewById(R.id.btnMod3);
        Button btnMod4 = findViewById(R.id.btnMod4);
        Button btnMod5 = findViewById(R.id.btnMod5);
        Button btnMod6 = findViewById(R.id.btnMod6);
    }
}