package com.icc.daelimi;

/**
 *   메인액티비티는 하단 네비뷰와 프레임 레이아웃으로 각 프레그먼트를 이동함
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ChatFragment chatFragment;
    SmartCampusFragment smartCampusFragment;
    EveryTimeFragment everyTimeFragment;
    ReportFragment reportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //앱 시작시 챗봇 화면을 기본으로 보여줌
        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, chatFragment).commit();

        showAlertDialog();

        //하단 네비뷰에서 선택한 메뉴에 따라 프레임 레이아웃에 표시되는 프레그먼트를 교체
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menuDaelimi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, chatFragment).commit();
                        return true;

                    case R.id.menuSmartcapus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, smartCampusFragment).commit();
                        return true;

                    case R.id.menuEverytime:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, everyTimeFragment).commit();
                        return true;

                    case R.id.menuInquiry:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, reportFragment).commit();
                        return true;
                }

              return true;
            }
        });

    }

    //앱 시작시 안내 알림 표시
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("안내");
        builder.setMessage("강의실 검색시 강의실명이 아닌 강의실 코드로 검색해주세요.\n"+
                "예) 전산관 프로그래밍1실 -> J0225");
        builder.setPositiveButton("알겠습니다", null);
        builder.create().show();
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bnvNavi);

        chatFragment = new ChatFragment();
        smartCampusFragment = new SmartCampusFragment();
        everyTimeFragment = new EveryTimeFragment();
        reportFragment = new ReportFragment();
    }

}