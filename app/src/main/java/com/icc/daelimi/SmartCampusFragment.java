package com.icc.daelimi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SmartCampusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SmartCampusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SmartCampusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SamartCampusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SmartCampusFragment newInstance(String param1, String param2) {
        SmartCampusFragment fragment = new SmartCampusFragment();
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

    ImageView ivGif;

    //앱이 설치 되어있으면 앱으로 이동하고 없으면 마켓으로 이동
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_smart_campus, container, false);

        initView(viewGroup);

        Glide.with(this).load(R.raw.gifsmart).into(ivGif);

        Intent intent;
        try{
            intent = getActivity().getPackageManager().getLaunchIntentForPackage("kr.ac.dlu.atdc");
            startActivity(intent);
        }catch (Exception e){
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=kr.ac.dlu.atdc"));
            startActivity(intent);
        }

        return viewGroup;
    }

    public void initView(ViewGroup viewGroup) {
        ivGif = viewGroup.findViewById(R.id.ivGif);
    }

}