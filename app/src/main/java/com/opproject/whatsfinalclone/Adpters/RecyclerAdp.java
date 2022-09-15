package com.opproject.whatsfinalclone.Adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opproject.whatsfinalclone.ChatDetailsActivity;
import com.opproject.whatsfinalclone.Models.UsersModel;
import com.opproject.whatsfinalclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdp extends RecyclerView.Adapter<RecyclerAdp.ViewHolder>{

    ArrayList<UsersModel> list;
    Context context;

    public RecyclerAdp(ArrayList<UsersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_recycle_user,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersModel model = list.get(position);
        Picasso.get().load(model.getULogo()).placeholder(R.drawable.avatar).into(holder.imageView);
        holder.nameUser.setText(model.getUname());

        // for last message:
        FirebaseDatabase.getInstance().getReference().child("Chats")
                .child(FirebaseAuth.getInstance().getUid() + model.getUid()).orderByChild("timestamp")
                        .limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                holder.lastMsg.setText(dataSnapshot.child("msg").getValue(String.class));
                            }
                        }
//                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ChatDetailsActivity.class);
                intent.putExtra("username" , model.getUname());
                intent.putExtra("userid" , model.getUid());
                intent.putExtra("userImg" , model.getULogo());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView nameUser , lastMsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profileimg);
            nameUser = itemView.findViewById(R.id.uName);
            lastMsg = itemView.findViewById(R.id.uAbout);
        }
    }
}
