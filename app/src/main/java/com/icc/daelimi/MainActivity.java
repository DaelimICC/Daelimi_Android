package com.icc.daelimi;

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

        bottomNavigationView = findViewById(R.id.bnvNavi);

        chatFragment = new ChatFragment();
        smartCampusFragment = new SmartCampusFragment();
        everyTimeFragment = new EveryTimeFragment();
        reportFragment = new ReportFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, chatFragment).commit();

        showAlertDialog();

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

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("안내");
        builder.setMessage("강의실 검색시 강의실명이 아닌 강의실 코드로 검색해주세요.\n"+
                "예) 전산관 프로그래밍1실 -> J0225");
        builder.setPositiveButton("알겠습니다", null);
        builder.create().show();
    }

}