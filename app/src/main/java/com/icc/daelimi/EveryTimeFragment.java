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
 * Use the {@link EveryTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EveryTimeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EveryTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EveryTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EveryTimeFragment newInstance(String param1, String param2) {
        EveryTimeFragment fragment = new EveryTimeFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_smart_campus, container, false);

        initView(viewGroup);

        Glide.with(this).load(R.raw.gifevery).into(ivGif);


        Intent intent;
        try{
            intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.everytime.v2");
            startActivity(intent);
        }catch (Exception e){
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.everytime.v2"));
            startActivity(intent);
        }

        return viewGroup;
    }

    public void initView(ViewGroup viewGroup)
    {
        ivGif = viewGroup.findViewById(R.id.ivGif);
    }
}