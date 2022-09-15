package com.opproject.whatsfinalclone;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opproject.whatsfinalclone.Fragments.ChatsFrag;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread th = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(600);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent it = new Intent(splashScreen.this , signUpActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };
        th.start();
    }
}