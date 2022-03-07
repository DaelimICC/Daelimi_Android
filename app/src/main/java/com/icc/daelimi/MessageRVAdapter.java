package com.icc.daelimi;

/**
 *  ReclerView에 사용하기 위한 어댑터
 *
 *  ref. https://www.geeksforgeeks.org/how-to-create-a-chatbot-in-android-with-brainshop-api/
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter {
    private ArrayList<MessageModal> messageModalArrayList;
    private Context context;

    public MessageRVAdapter(ArrayList<MessageModal> messageModalArrayList, Context context){
        this.messageModalArrayList = messageModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg, parent, false);
                return new UserViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    //modal에 저장된 입력자를 판단하여 알맞은 메세지 박스에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModal modal = messageModalArrayList.get(position);

        switch (modal.getSender()){
            case "user":
                ((UserViewHolder)holder).tvUser.setText(modal.getMessage());
                break;

            case "bot":
                ((BotViewHolder)holder).tvBot.setText(modal.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageModalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (messageModalArrayList.get(position).getSender()){
            case "user":
                return 0;

            case "bot":
                return 1;

            default:
                return -1;
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser;

        public UserViewHolder(@NonNull View view) {
            super(view);
            tvUser = view.findViewById(R.id.tvUser);
        }
    }

    private class BotViewHolder extends RecyclerView.ViewHolder {
        TextView tvBot;

        public BotViewHolder(@NonNull View view) {
            super(view);
            tvBot = view.findViewById(R.id.tvBot);
        }
    }
}
