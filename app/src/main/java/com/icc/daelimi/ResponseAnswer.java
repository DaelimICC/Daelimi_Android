package com.icc.daelimi;

import com.google.gson.annotations.SerializedName;

import java.lang.String;

public class ResponseAnswer {
    @SerializedName("answer")
    private String answer;

    @Override
    public String toString() {
        return answer;
    }
}
