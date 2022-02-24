package com.example.daelimi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String themeColor;
    Button btSmartCampus, btEverytime, btColor, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();


        themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        findId();

    }

    public boolean getPackageList(String adrass) {
        boolean isExist = false;

        PackageManager pkgMgr = getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < ((List) mApps).size(); i++) {
                if(mApps.get(i).activityInfo.packageName.startsWith(adrass)){
                    isExist = true;
                    break;
                }
            }
        }
        catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }

    public void btSmartCampus(View view) {
        boolean site = getPackageList("kr.ac.dlu.atdc");
        if (site == true){
            Intent intent = getPackageManager().getLaunchIntentForPackage("kr.ac.dlu.atdc");
            startActivity(intent);
        }
        else {
            String url = "market://details?id=" + "kr.ac.dlu.atdc";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    public void btEverytime(View view) {
        boolean site = getPackageList("com.everytime.v2");
        if (site == true){
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.everytime.v2");
            startActivity(intent);
        }
        else {
            String url = "market://details?id=" + "com.everytime.v2";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    public void btcolor(View view) {
        Intent intent = new Intent(getApplicationContext(),ModDialog.class);
        startActivity(intent);
    }

    public void findId() {
        btSmartCampus = findViewById(R.id.bt_SmartCampus);
        btEverytime = findViewById(R.id.bt_Everytime);
        btColor = findViewById(R.id.bt_color);
    }
}