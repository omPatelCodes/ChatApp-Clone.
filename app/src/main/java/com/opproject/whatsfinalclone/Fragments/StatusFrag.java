package com.opproject.whatsfinalclone.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opproject.whatsfinalclone.Adpters.RecyclerAdp;
import com.opproject.whatsfinalclone.Models.UsersModel;
import com.opproject.whatsfinalclone.R;
import com.opproject.whatsfinalclone.databinding.FragmentStatusBinding;
import com.squareup.picasso.Picasso;

public class StatusFrag extends Fragment {

    FragmentStatusBinding binding;

    public StatusFrag() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersModel model = snapshot.getValue(UsersModel.class);
                Picasso.get().load(model.getULogo()).placeholder(R.drawable.avatar).into(binding.profileimg);
                binding.uName.setText(model.getUname());
                binding.uAbout.setText(model.getAbout());
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
        binding = FragmentStatusBinding.inflate(inflater , container, false);

        binding.touchlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Next Update we can see the status...", Toast.LENGTH_LONG).show();
            }
        });
        return binding.getRoot();
    }
}