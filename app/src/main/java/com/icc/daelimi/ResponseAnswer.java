package com.icc.daelimi;

/**
 * 서버에서 응답 결과로 받은 responese를 저장할 객체
 * 서버의 응답 메세지를 저장하는 answer
 */

import com.google.gson.annotations.SerializedName;

import java.lang.String;

public class ResponseAnswer {
    @SerializedName("answer")
    private String answer;

    //toString을 그냥 사용하면 주소값을 출력하므로 오버라이드 하여 사용
    @Override
    public String toString() {
        return answer;
    }
}
