package com.icc.daelimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rvChats;
    EditText edtMessage;
    FloatingActionButton fabSend;

    final String USER_KEY = "user";
    final String BOT_KEY = "bot";

    ArrayList<MessageModal> messageModalArrayList;
    MessageRVAdapter messageRVAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://141.164.61.172:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);
    Call<ResponseAnswer> call;

    Filter filter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        filter = new Filter();

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);

        initView(viewGroup);

        messageModalArrayList = new ArrayList<>();

        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvChats.setLayoutManager(linearLayoutManager);
        rvChats.setAdapter(messageRVAdapter);

        setBotMessage("안녕하세요! 대림이 입니다.");

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = edtMessage.getText().toString();
                if (userMessage.isEmpty()) {
                    Toast.makeText(getContext(), "질문을 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }

                sendMessage(userMessage);

                edtMessage.setText("");

                fabSend.setClickable(false);
            }
        });

        return viewGroup;
    }

    private void sendMessage(String userMessage) {
        messageModalArrayList.add(new MessageModal(userMessage, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        call = service.getResponse(filter.sendMessage(userMessage));

        call.enqueue(new Callback<ResponseAnswer>() {
            @Override
            public void onResponse(Call<ResponseAnswer> call, Response<ResponseAnswer> response) {
                if(response.isSuccessful()){
                    ResponseAnswer result = response.body();
                    String responseMessage = result.toString();
                    setBotMessage(responseMessage);
                }
            }

            @Override
            public void onFailure(Call<ResponseAnswer> call, Throwable t) {
                setBotMessage("서버연결에 문제가 생겼습니다.");
            }
        });
    }

    private void setBotMessage(String botMessage){
        messageModalArrayList.add(new MessageModal(botMessage, BOT_KEY));
        messageRVAdapter.notifyDataSetChanged();
        rvChats.smoothScrollToPosition(messageModalArrayList.size());
        fabSend.setClickable(true);
    }

    public void initView(ViewGroup viewGroup) {
        rvChats = viewGroup.findViewById(R.id.rvChats);
        edtMessage = viewGroup.findViewById(R.id.edtMessage);
        fabSend = viewGroup.findViewById(R.id.fabSend);
    }
}

