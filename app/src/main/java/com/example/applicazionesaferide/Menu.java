package com.example.applicazionesaferide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Menu extends AppCompatActivity {

    boolean modificabile1 = false;
    boolean modificabile2 = false;
    boolean modificabile3 = false;
    boolean modificabile4 = false;
    boolean modificabile5 = false;
    boolean modificabile6 = false;
    Socket s = null;
    DataOutputStream dout = null;

    SharedPreferences sp;

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
        Button btnSalvaMod = findViewById(R.id.salvaModifiche);

        EditText txt1 = findViewById(R.id.EDtelefonoCell);
        EditText txt2 = findViewById(R.id.EDtelefonoRis);
        EditText txt3 = findViewById(R.id.EDnome);
        EditText txt4 = findViewById(R.id.EDcognome);
        EditText txt5 = findViewById(R.id.EDcodiceFiscale);
        EditText txt6 = findViewById(R.id.EDcodDisp);

        ImageView iv1 = findViewById(R.id.mod1);
        ImageView iv2 = findViewById(R.id.mod2);
        ImageView iv3 = findViewById(R.id.mod3);
        ImageView iv4 = findViewById(R.id.mod4);
        ImageView iv5 = findViewById(R.id.mod5);
        ImageView iv6 = findViewById(R.id.mod6);

        Drawable edit = getResources().getDrawable(R.drawable.ic_baseline_edit_24);
        Drawable check = getResources().getDrawable(R.drawable.ic_baseline_check_24);


        sp = getSharedPreferences("DatiUtenti", Context.MODE_PRIVATE);
        txt1.setText(sp.getString("telCell",""));
        txt2.setText(sp.getString("telRis",""));
        txt3.setText(sp.getString("nome",""));
        txt4.setText(sp.getString("cognome",""));
        txt5.setText(sp.getString("cf",""));
        txt6.setText(sp.getString("cDisp",""));





        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);




        btnSalvaMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("telCell", txt1.getText().toString());
                editor.putString("telRis", txt2.getText().toString());
                editor.putString("nome", txt3.getText().toString());
                editor.putString("cognome", txt4.getText().toString());
                editor.putString("cf", txt5.getText().toString());
                editor.putString("cDisp", txt6.getText().toString());
                editor.commit();

                Thread thread = new Thread() {
                    @Override
                    public void run() {

                        try {
                            s = new Socket("192.168.4.1", 25200);
                            String msgDati = txt1.getText().toString() + "," +  txt2.getText().toString() + "," +  txt3.getText().toString() + "," +  txt4.getText().toString() + "," +  txt5.getText().toString() + "," +  txt6.getText().toString();
                            dout = new DataOutputStream(s.getOutputStream());
                            dout.writeUTF(msgDati);
                            dout.flush();
                            dout.close();
                            s.close();
                        } catch (IOException e) {
                            //e.printStackTrace();
                        }

                    }
                };

                try {
                    thread.start();
                }catch(Exception ex){

                }

            }
        });





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
                    View viewPage = getCurrentFocus();
                    
                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);


                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);

                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);

                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);

                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);





                    ////////////////////CONTINUARE A FARE LA STESSA COSA.........../////////



                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));//<-------------//
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);

                    modificabile1 = true;
                    txt1.setClickable(true);
                    txt1.setCursorVisible(true);
                    txt1.setFocusable(true);
                    txt1.setFocusableInTouchMode(true);
                    txt1.requestFocus();
                    // imm.showSoftInput(txt1,InputMethodManager.SHOW_FORCED );
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv1.setImageDrawable(check);
                    btnMod1.setBackgroundColor(Color.parseColor("#9916E2"));
                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);
                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));

                }
            }
        });

        btnMod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile2) {
                    View viewPage = getCurrentFocus();
                    
                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);

                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);

                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);

                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);

                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);

                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);

                    modificabile2 = true;
                    txt2.setClickable(true);
                    txt2.setCursorVisible(true);
                    txt2.setFocusable(true);
                    txt2.setFocusableInTouchMode(true);
                    txt2.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv2.setImageDrawable(check);
                    btnMod2.setBackgroundColor(Color.parseColor("#9916E2"));
                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);
                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                }
            }
        });

        btnMod3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile3) {
                    View viewPage = getCurrentFocus();
                    
                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);

                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);

                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);

                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);

                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);

                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);

                    modificabile3 = true;
                    txt3.setClickable(true);
                    txt3.setCursorVisible(true);
                    txt3.setFocusable(true);
                    txt3.setFocusableInTouchMode(true);
                    txt3.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv3.setImageDrawable(check);
                    btnMod3.setBackgroundColor(Color.parseColor("#9916E2"));

                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);
                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                }
            }
        });

        btnMod4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile4) {
                    View viewPage = getCurrentFocus();
                    
                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);

                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);

                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);

                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);

                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);

                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);

                    modificabile4 = true;
                    txt4.setClickable(true);
                    txt4.setCursorVisible(true);
                    txt4.setFocusable(true);
                    txt4.setFocusableInTouchMode(true);
                    txt4.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv4.setImageDrawable(check);
                    btnMod4.setBackgroundColor(Color.parseColor("#9916E2"));

                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);
                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                }
            }
        });

        btnMod5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile5) {
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);

                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);

                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);

                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);

                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);

                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);

                    modificabile5 = true;
                    txt5.setClickable(true);
                    txt5.setCursorVisible(true);
                    txt5.setFocusable(true);
                    txt5.setFocusableInTouchMode(true);
                    txt5.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv5.setImageDrawable(check);
                    btnMod5.setBackgroundColor(Color.parseColor("#9916E2"));

                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);
                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                }
            }
        });

        btnMod6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modificabile6) {
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);

                    btnMod1.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile1 = false;
                    txt1.setClickable(false);
                    txt1.setCursorVisible(false);
                    txt1.setFocusable(false);
                    txt1.setFocusableInTouchMode(false);
                    iv1.setImageDrawable(edit);

                    btnMod2.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile2 = false;
                    txt2.setClickable(false);
                    txt2.setCursorVisible(false);
                    txt2.setFocusable(false);
                    txt2.setFocusableInTouchMode(false);
                    iv2.setImageDrawable(edit);

                    btnMod3.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile3 = false;
                    txt3.setClickable(false);
                    txt3.setCursorVisible(false);
                    txt3.setFocusable(false);
                    txt3.setFocusableInTouchMode(false);
                    iv3.setImageDrawable(edit);

                    btnMod4.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile4 = false;
                    txt4.setClickable(false);
                    txt4.setCursorVisible(false);
                    txt4.setFocusable(false);
                    txt4.setFocusableInTouchMode(false);
                    iv4.setImageDrawable(edit);

                    btnMod5.setBackgroundColor(Color.parseColor("#0016E2"));
                    modificabile5 = false;
                    txt5.setClickable(false);
                    txt5.setCursorVisible(false);
                    txt5.setFocusable(false);
                    txt5.setFocusableInTouchMode(false);
                    iv5.setImageDrawable(edit);

                    modificabile6 = true;
                    txt6.setClickable(true);
                    txt6.setCursorVisible(true);
                    txt6.setFocusable(true);
                    txt6.setFocusableInTouchMode(true);
                    txt6.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    iv6.setImageDrawable(check);
                    btnMod6.setBackgroundColor(Color.parseColor("#9916E2"));

                }else{
                    View viewPage = getCurrentFocus();

                    if (viewPage == null) {
                        viewPage = new View(Menu.this);
                    }
                    imm.hideSoftInputFromWindow(viewPage.getWindowToken(), 0);
                    modificabile6 = false;
                    txt6.setClickable(false);
                    txt6.setCursorVisible(false);
                    txt6.setFocusable(false);
                    txt6.setFocusableInTouchMode(false);
                    iv6.setImageDrawable(edit);
                    btnMod6.setBackgroundColor(Color.parseColor("#0016E2"));


                }
            }
        });
    }
}