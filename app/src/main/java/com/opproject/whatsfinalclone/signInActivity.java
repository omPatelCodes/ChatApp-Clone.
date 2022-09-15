package com.opproject.whatsfinalclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.opproject.whatsfinalclone.databinding.ActivitySignInBinding;

public class signInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);

        dialog.setMessage("Wait we're find details...");
        dialog.setTitle("Loading...");

        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                auth.signInWithEmailAndPassword(binding.emailSI.getText().toString(),binding.passWordSI.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if (task.isSuccessful()){
                                    Toast.makeText(signInActivity.this, "account login successfully", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(signInActivity.this , MainActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else {
                                    Toast.makeText(signInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }



    public void signUp(View view) {
        Intent it = new Intent(signInActivity.this , signUpActivity.class);
        startActivity(it);
        finish();
    }

}