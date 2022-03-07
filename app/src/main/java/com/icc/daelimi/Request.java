package com.icc.daelimi;

/**
 * 서버에 보낼 Request객체
 * 일반질문 모델과 장소 모델을 구분하기 위한 isFilter.
 * 사용자의 질문을 담을 message.
 */

import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("isFilter")
    private int isFilter;

    @SerializedName("message")
    private String message;

    public Request(int isFilter, String message) {
        this.isFilter = isFilter;
        this.message = message;
    }

}
