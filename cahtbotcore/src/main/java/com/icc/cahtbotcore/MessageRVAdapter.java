package com.icc.cahtbotcore;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter {
    private ArrayList<MessageModal> messageModalArrayList;
    private Context context;
    private Layout  user_msg, bot_msg;

    public MessageRVAdapter(ArrayList<MessageModal> messageModalArrayList, Context context){
        this.messageModalArrayList = messageModalArrayList;
        this.context = context;
    }

    public void setBot_msg(Layout bot_msg) {
        this.bot_msg = bot_msg;
    }

    public void setUser_msg(Layout user_msg){
        this.user_msg = user_msg;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate((XmlPullParser) user_msg, parent, false);
                return new UserViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate((XmlPullParser) bot_msg, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

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
