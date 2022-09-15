package com.opproject.whatsfinalclone;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.opproject.whatsfinalclone.Adpters.ReplaceFragAdp;
import com.opproject.whatsfinalclone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewpager.setAdapter(new ReplaceFragAdp(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                Intent it = new Intent(MainActivity.this , signUpActivity.class);
                startActivity(it);
                finish();
                break;

            case R.id.gChat:
                Intent intent = new Intent(MainActivity.this , GroupChatActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting:
                Intent intent2 = new Intent(MainActivity.this , SettingActivity.class);
                startActivity(intent2);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}