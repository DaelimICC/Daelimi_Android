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
    RecyclerView rvChats;
    EditText edtMessage;
    FloatingActionButton fabSend;

    final String USER_KEY = "user";
    final String BOT_KEY = "bot";

    ArrayList<MessageModal> messageModalArrayList;
    MessageRVAdapter messageRVAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://asia-northeast1-root-station-341716.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);
    Call<ResponseAnswer> call;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);

        rvChats = viewGroup.findViewById(R.id.rvChats);
        edtMessage = viewGroup.findViewById(R.id.edtMessage);
        fabSend = viewGroup.findViewById(R.id.fabSend);


        messageModalArrayList = new ArrayList<>();

        try {
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

                }
            });

            messageRVAdapter = new MessageRVAdapter(messageModalArrayList, getContext());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            rvChats.setLayoutManager(linearLayoutManager);
            rvChats.setAdapter(messageRVAdapter);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return viewGroup;
    }

    private void sendMessage(String userMessage) {
        messageModalArrayList.add(new MessageModal(userMessage, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        call = service.getResponse(new Request("1", userMessage));
        /*if(){
            call = service.getResponse(new Request("1", userMessage));
        }else{
            call = service.getResponse(new Request("0", userMessage));
        }*/

        call.enqueue(new Callback<ResponseAnswer>() {
            @Override
            public void onResponse(Call<ResponseAnswer> call, Response<ResponseAnswer> response) {
                if(response.isSuccessful()){
                    ResponseAnswer result = response.body();
                    String responseMessage = result.toString();
                    messageModalArrayList.add(new MessageModal(responseMessage, BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                    rvChats.smoothScrollToPosition(messageModalArrayList.size());
                }
            }

            @Override
            public void onFailure(Call<ResponseAnswer> call, Throwable t) {
                messageModalArrayList.add(new MessageModal("서버 연결에 오류가 생겼습니다.", BOT_KEY));
                messageRVAdapter.notifyDataSetChanged();
                rvChats.smoothScrollToPosition(messageModalArrayList.size());
            }

        });


    }
}

