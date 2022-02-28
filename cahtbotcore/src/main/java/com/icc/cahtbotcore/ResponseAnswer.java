package com.icc.cahtbotcore;

import com.google.gson.annotations.SerializedName;

public class ResponseAnswer {
    @SerializedName("answer")
    private String answer;

    @Override
    public String toString() {
        return answer;
    }
}
