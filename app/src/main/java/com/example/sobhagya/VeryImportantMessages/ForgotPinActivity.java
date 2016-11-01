package com.example.sobhagya.VeryImportantMessages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPinActivity extends Activity {
LoginData ld;
    EditText ed1, ed2;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pin);

        ed1 = (EditText) findViewById(R.id.editText9);
        ed2 = (EditText) findViewById(R.id.editText10);
        bt1 = (Button)  findViewById(R.id.button8);
        bt2 = (Button)  findViewById(R.id.button9);
        ld = new LoginData(this);

        forgotpin();
    }

    public void forgotpin(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = ed1.getText().toString();
                String pas = ed2.getText().toString();
                if (us.isEmpty()) {
                    nmess("Error", "Enter username!");
                } else if (pas.isEmpty()) {
                    nmess("Error", "Enter password!");
                } else if (!us.isEmpty() && !pas.isEmpty()) {
                    Boolean ch = ld.retpin(us, pas);
                    if (ch==false) {
                        nmess("Error", "Wrong username or password!");
                    } else {
 //                       nmess("Note the PIN :", us_pin);
 //                       Thread thread = new Thread() {
 //                           public void run() {
 //                               try {
 //                                   sleep(5000);
 //                               } catch (Exception e) {

 //                               } finally {
                                    Intent intent = new Intent(ForgotPinActivity.this, PinActivity.class);
                                    startActivity(intent);
//                                }
//                            }
//                        };
//                        thread.start();
                    }
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = ed1.getText().toString();
                String pas = ed2.getText().toString();
                if (us.isEmpty()) {
                    nmess("Error", "Enter username!");
                } else if (pas.isEmpty()) {
                    nmess("Error", "Enter password!");
                } else if (!us.isEmpty() && !pas.isEmpty()) {
                    Boolean ch = ld.retpin(us, pas);
                    if (ch == false) {
                        nmess("Error", "Wrong username or password!");
                    } else {
                        boolean a = ld.del1();
                        if(a)
                            Toast.makeText(ForgotPinActivity.this,"Account Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ForgotPinActivity.this,"Account not Deleted",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgotPinActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ld.close();
        finish();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void nmess(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
