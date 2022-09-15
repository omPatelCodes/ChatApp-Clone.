package com.opproject.whatsfinalclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opproject.whatsfinalclone.Adpters.ChatsAdp;
import com.opproject.whatsfinalclone.Models.MsgModel;
import com.opproject.whatsfinalclone.databinding.ActivityGroupChatBinding;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });
        binding.username.setText("Whole group...");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        final ArrayList<MsgModel> list = new ArrayList<>();
        ChatsAdp adp = new ChatsAdp(list , this);
        binding.recyclerChat.setAdapter(adp);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerChat.setLayoutManager(manager);

        String senderId = auth.getUid();
        database.getReference().child("Group Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MsgModel model2 = dataSnapshot.getValue(MsgModel.class);
                    list.add(model2);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendMsgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txt = binding.etMsg.getText().toString();
                final MsgModel model = new MsgModel(senderId,txt);
                model.setTime(new Date().getTime());
                binding.etMsg.setText("");
                database.getReference().child("Group Chat")
                        .push()
                        .setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GroupChatActivity.this , MainActivity.class);
        startActivity(intent);
    }
}