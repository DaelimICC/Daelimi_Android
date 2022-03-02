package com.icc.daelimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InquiryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InquiryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InquiryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InquiryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InquiryFragment newInstance(String param1, String param2) {
        InquiryFragment fragment = new InquiryFragment();
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

    WebView wvView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_inquiry, container, false);

        wvView = viewGroup.findViewById(R.id.wvView);

        wvView.setWebViewClient(new WebViewClient());
        wvView.setWebChromeClient(new WebChromeClient());

        //WebView 화면크기에 맞추기
        wvView.getSettings().setLoadWithOverviewMode(true);
        wvView.getSettings().setUseWideViewPort(true);

        //WebView 줌 설정 여부
        wvView.getSettings().setSupportZoom(true);

        wvView.loadUrl("http://141.164.61.172:8000/issue");

        return viewGroup;

    }
}