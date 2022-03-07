package com.icc.daelimi;

/**
 * 초기 로딩을 위한 스플래쉬 화면이다.
 * 1초간 스플래쉬 화면을 보여주고 메인 액티비티를 보여준다.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TitleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        moveMain(1);
    }

    //스플래쉬 화면을 지정한 시간 만큼 보여주고 메인화면 출력
    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

                finish();
            }
        }, 1000 * sec); // sec초 정도 딜레이를 준 후 시작
    }
}