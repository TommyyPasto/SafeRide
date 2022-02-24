package com.example.applicazionesaferide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu extends AppCompatActivity {

    boolean modificabile1 = false;
    boolean modificabile2 = false;
    boolean modificabile3 = false;
    boolean modificabile4 = false;
    boolean modificabile5 = false;
    boolean modificabile6 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnMod1 = findViewById(R.id.btnMod1);
        Button btnMod2 = findViewById(R.id.btnMod2);
        Button btnMod3 = findViewById(R.id.btnMod3);
        Button btnMod4 = findViewById(R.id.btnMod4);
        Button btnMod5 = findViewById(R.id.btnMod5);
        Button btnMod6 = findViewById(R.id.btnMod6);

        EditText txt1 = findViewById(R.id.EDtelefonoCell);
        EditText txt2 = findViewById(R.id.EDtelefonoRis);
        EditText txt3 = findViewById(R.id.EDnome);
        EditText txt4 = findViewById(R.id.EDcognome);
        EditText txt5 = findViewById(R.id.EDcodiceFiscale);
        EditText txt6 = findViewById(R.id.EDcodDisp);


        /*
            android:clickable="false"
            android:cursorVisible="false"
            android:focusable="false"
            android:focusableInTouchMode="false"
         */
        btnMod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile1) {
                    modificabile1 = true;
                    txt1.setClickable(true);
                    txt1.setCursorVisible(true);
                    txt1.setFocusable(true);
                    txt1.setFocusableInTouchMode(true);

                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);

                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);

                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);

                    modificabile5 = false;
                    modificabile6 = false;
                }else{
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                }
            }
        });

        btnMod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile2) {
                    modificabile2 = true;
                    txt2.setClickable(true);
                    txt2.setCursorVisible(true);
                    txt2.setFocusable(true);
                    txt2.setFocusableInTouchMode(true);

                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);

                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);

                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);

                    modificabile5 = false;
                    modificabile6 = false;
                }else{
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                }
            }
        });

        btnMod3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile3) {
                    modificabile3 = true;
                    txt3.setClickable(true);
                    txt3.setCursorVisible(true);
                    txt3.setFocusable(true);
                    txt3.setFocusableInTouchMode(true);

                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);

                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);

                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);

                    modificabile5 = false;
                    modificabile6 = false;
                }else{
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                }
            }
        });

        btnMod4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile4) {
                    modificabile4 = true;
                    txt4.setClickable(true);
                    txt4.setCursorVisible(true);
                    txt4.setFocusable(true);
                    txt4.setFocusableInTouchMode(true);
                    modificabile1 = false;
                    modificabile2 = false;
                    modificabile3 = false;
                    modificabile5 = false;
                    modificabile6 = false;
                }else{
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                }
            }
        });

        btnMod5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMod6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}