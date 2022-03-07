package com.icc.daelimi;

/**
 * 챗봇을 구현한 프레그먼트로 앱 실행시 표시되는 프레그먼트입니다.
 *
 * Adapter, Modal, MwssageView는 하단 사이트를 참조하여 구현되었습니다.
 *  ref. https://www.geeksforgeeks.org/how-to-create-a-chatbot-in-android-with-brainshop-api/
 */

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
    String responseMessage;

    ArrayList<MessageModal> messageModalArrayList;
    MessageRVAdapter messageRVAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api-daelimi.soplay.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);
    Call<ResponseAnswer> call;

    Filter filter; //사용자 메세지를 판별하여 필터값을 정한 Request 객체 생성

    //챗봇 Modal, adapter를 구현 하고 통신 처리
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);

        init(viewGroup);

        messageModalArrayList = new ArrayList<>();

        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvChats.setLayoutManager(linearLayoutManager);
        rvChats.setAdapter(messageRVAdapter);

        setBotMessage("안녕하세요! 대림이 입니다."); //챗봇 처음 메세지 출력

        //메세지를 전송하고 답변이 올때 까지 전송 버튼을 비활성화함
        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = edtMessage.getText().toString();
                if (userMessage.isEmpty()) {
                    Toast.makeText(getContext(), "질문을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendMessage(userMessage);

                edtMessage.setText("");

                fabSend.setClickable(false);
            }
        });

        return viewGroup;
    }

    //사용자 메세지를 서버에 전송
    private void sendMessage(String userMessage) {
        messageModalArrayList.add(new MessageModal(userMessage, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        call = service.getResponse(filter.sendMessage(userMessage));

        call.enqueue(new Callback<ResponseAnswer>() {
            //통신 성공시 response의 바디에서 메세지를 가져와 출력
            @Override
            public void onResponse(Call<ResponseAnswer> call, Response<ResponseAnswer> response) {
                //응답코드 2XX만 정상으로 처리
               if(response.isSuccessful()){
                    ResponseAnswer result = response.body();
                    responseMessage = result.toString();
                    setBotMessage(responseMessage);
                }
            }

            //통신 실패시 에러메세지 출력
            @Override
            public void onFailure(Call<ResponseAnswer> call, Throwable t) {
                setBotMessage("서버연결에 문제가 생겼습니다.");
            }
        });
    }

    //서버에서 응답한 메세지를 봇 메세지로 출력
    private void setBotMessage(String botMessage){
        messageModalArrayList.add(new MessageModal(botMessage, BOT_KEY));
        messageRVAdapter.notifyDataSetChanged();
        rvChats.smoothScrollToPosition(messageModalArrayList.size());
        fabSend.setClickable(true);
    }

    public void init(ViewGroup viewGroup) {
        rvChats = viewGroup.findViewById(R.id.rvChats);
        edtMessage = viewGroup.findViewById(R.id.edtMessage);
        fabSend = viewGroup.findViewById(R.id.fabSend);

        filter = new Filter();
    }
}

