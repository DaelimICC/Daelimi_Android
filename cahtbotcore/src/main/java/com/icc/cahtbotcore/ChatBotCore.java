package com.icc.cahtbotcore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotCore {
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private String path, isFilter;

    ArrayList<MessageModal> messageModalArrayList = new ArrayList<>();
    MessageRVAdapter messageRVAdapter;

    Retrofit retrofit;
    RetrofitService service;
    Call<ResponseAnswer> call;

    MessageFilter messageFilter = new MessageFilter();

    public ChatBotCore(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);
    }

    public void getService(){

    }

    public void setPath(String path) {
        this.path = path;
    }
}
