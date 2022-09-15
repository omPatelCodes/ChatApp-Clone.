package com.opproject.whatsfinalclone.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opproject.whatsfinalclone.Adpters.RecyclerAdp;
import com.opproject.whatsfinalclone.Models.UsersModel;
import com.opproject.whatsfinalclone.R;
import com.opproject.whatsfinalclone.databinding.FragmentChatsBinding;
import com.opproject.whatsfinalclone.signUpActivity;
import com.opproject.whatsfinalclone.splashScreen;

import java.util.ArrayList;

public class ChatsFrag extends Fragment {

    public ChatsFrag() {
        // Required empty public constructor
    }

    FragmentChatsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<UsersModel> list = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        RecyclerAdp adp = new RecyclerAdp(list , getContext());
        binding.recyclerView.setAdapter(adp);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(manager);


        database = FirebaseDatabase.getInstance();

        database.getReference().child("UserData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UsersModel userData = dataSnapshot.getValue(UsersModel.class);
                    userData.setUid(dataSnapshot.getKey());

                    if (!userData.getUid().equals(FirebaseAuth.getInstance().getUid())){
                        list.add(userData);
                    }
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        return binding.getRoot();
    }
}