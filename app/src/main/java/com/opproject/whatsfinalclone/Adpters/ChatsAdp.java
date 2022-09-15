package com.opproject.whatsfinalclone.Adpters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.opproject.whatsfinalclone.Models.MsgModel;
import com.opproject.whatsfinalclone.R;

import java.util.ArrayList;


public class ChatsAdp extends RecyclerView.Adapter{

    ArrayList<MsgModel> list;
    Context context;
    String recId;

    int SENDER = 1;
    int RECEIVER = 2;


    public ChatsAdp(ArrayList<MsgModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ChatsAdp(ArrayList<MsgModel> list, Context context, String recId) {
        this.list = list;
        this.context = context;
        this.recId = recId;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER;
        }
        else {
            return RECEIVER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER){
            View v = LayoutInflater.from(context).inflate(R.layout.sample_send, parent , false);
            return new SenderViewHolder(v);
        }
        else {
            View v = LayoutInflater.from(context).inflate(R.layout.sample_receiver, parent , false);
            return new ReceiverViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MsgModel model = list.get(position);
        if (holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder) holder).msgS.setText(model.getMsg());
        }
        else {
            ((ReceiverViewHolder)holder).msgR.setText(model.getMsg());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("DELETE").setMessage("Are You Sure You want to delete message...").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String SenderRoom = FirebaseAuth.getInstance().getUid() + recId;
                        database.getReference().child("Chats").child(SenderRoom).child(model.getMsgId()).setValue(null);
                    }
                }).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("DELETE").setMessage("Are You Sure You want to delete message...").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String SenderRoom = FirebaseAuth.getInstance().getUid() + recId;
                        database.getReference().child("Chats").child(SenderRoom).child(model.getMsgId()).setValue(null);
                    }
                }).show();
                return false;

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView msgS , timeS;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            msgS = itemView.findViewById(R.id.sMsg);
            timeS = itemView.findViewById(R.id.sTime);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView msgR , timeR;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            msgR = itemView.findViewById(R.id.rMsg);
            timeR = itemView.findViewById(R.id.rTime);
        }
    }

}
