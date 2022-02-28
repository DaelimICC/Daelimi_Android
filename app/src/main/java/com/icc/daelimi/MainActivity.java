package com.icc.daelimi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ChatFragment chatFragment;
    SmartCampusFragment smartCampusFragment;
    EveryTimeFragment everyTimeFragment;
    InquiryFragment inquiryFragment;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bnvNavi);
        chatFragment = new ChatFragment();
        smartCampusFragment = new SmartCampusFragment();
        everyTimeFragment = new EveryTimeFragment();
        inquiryFragment = new InquiryFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, chatFragment).commit();

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
                        getSupportFragmentManager().beginTransaction().replace(R.id.flyFrame, inquiryFragment).commit();
                        return true;
                }

              return true;
            }
        });
    }
}