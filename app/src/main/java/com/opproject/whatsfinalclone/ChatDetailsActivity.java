package com.opproject.whatsfinalclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opproject.whatsfinalclone.Adpters.ChatsAdp;
import com.opproject.whatsfinalclone.Models.MsgModel;
import com.opproject.whatsfinalclone.databinding.ActivityChatDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailsActivity extends AppCompatActivity {

    ActivityChatDetailsBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String sendId = auth.getUid();
        String receiveId = getIntent().getStringExtra("userid");
        Picasso.get().load(getIntent().getStringExtra("userImg")).placeholder(R.drawable.avatar).into(binding.pimg);
        binding.username.setText(getIntent().getStringExtra("username"));



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ChatDetailsActivity.this , MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        final ArrayList<MsgModel> list = new ArrayList<>();

        ChatsAdp adp = new ChatsAdp(list , this , receiveId);
        binding.recyclerChat.setAdapter(adp);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerChat.setLayoutManager(manager);


        final String senderRoom = sendId + receiveId;
        final String receiverRoom = receiveId + sendId;

        binding.sendMsgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.etMsg.getText().toString();
                final MsgModel model = new MsgModel(sendId,msg);
                model.setTime(new Date().getTime());
                binding.etMsg.setText("");

                database.getReference().child("Chats").child(senderRoom)
                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference().child("Chats").child(receiverRoom)
                                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                            }
                                        });
                            }
                        });

            }
        });

        database.getReference().child("Chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MsgModel model2 = dataSnapshot.getValue(MsgModel.class);
                    model2.setMsgId(dataSnapshot.getKey());
                    list.add(model2);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void CallUser(View view) {
        Toast.makeText(this, "User Number is not found", Toast.LENGTH_SHORT).show();
    }
}