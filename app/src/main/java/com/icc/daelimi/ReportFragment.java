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
 * 문의 사이트를 연결해주는 프레그먼트이다.
 */
public class ReportFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
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
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
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

    //웹뷰를 생성하여 설정하고 문의사이트로 연결
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_report, container, false);

        initWebView(viewGroup);

        wvView.loadUrl("https://daelimi.soplay.dev/error_report_mobile");

        return viewGroup;
    }

    public void initWebView(ViewGroup viewGroup){
        wvView = viewGroup.findViewById(R.id.wvView);

        wvView.setWebViewClient(new WebViewClient());
        wvView.setWebChromeClient(new WebChromeClient());

        //WebView 화면크기에 맞추기
        wvView.getSettings().setLoadWithOverviewMode(true);
        wvView.getSettings().setUseWideViewPort(true);

        //WebView 줌 설정 여부
        wvView.getSettings().setSupportZoom(true);

        //WebView 자바스크립트 허용 여부
        wvView.getSettings().setJavaScriptEnabled(true);

        //WebView 모바일장치 내부저장소 이용 허용 여부
        wvView.getSettings().setDomStorageEnabled(true);
    }
}