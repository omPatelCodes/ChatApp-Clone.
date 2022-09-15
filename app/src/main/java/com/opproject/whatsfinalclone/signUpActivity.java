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
import com.opproject.whatsfinalclone.Models.UsersModel;
import com.opproject.whatsfinalclone.databinding.ActivitySignUpBinding;

public class signUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ProgressDialog dialog ;

    public ProgressDialog getDialog() {
        dialog.setTitle("Creating Account...");
        dialog.setMessage("We're Creating your Account");
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);

        if (auth.getCurrentUser() != null){
            Intent it = new Intent(signUpActivity.this , MainActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void signUp(View view) {
        if (binding.emailSU.getText().toString().endsWith("@gmail.com")){
        getDialog().show();
        auth.createUserWithEmailAndPassword(binding.emailSU.getText().toString(),binding.passWordSU.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        getDialog().dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(signUpActivity.this, "Created Account Succesfully", Toast.LENGTH_SHORT).show();

                            UsersModel model = new UsersModel(binding.userNameSU.getText().toString() , binding.emailSU.getText().toString(),binding.passWordSU.getText().toString());

                            String Uid = task.getResult().getUser().getUid();

                            database.getReference().child("UserData").child(Uid).setValue(model);

                            Intent it = new Intent(signUpActivity.this,MainActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else {
                            Toast.makeText(signUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
        else {
            Toast.makeText(this, "your data is not formatted...", Toast.LENGTH_SHORT).show();
        }
    }

    public void signIN(View view) {
        Intent it = new Intent(signUpActivity.this , signInActivity.class);
        startActivity(it);
        finish();
    }
}