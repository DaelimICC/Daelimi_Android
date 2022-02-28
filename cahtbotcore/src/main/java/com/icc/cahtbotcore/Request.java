package com.icc.cahtbotcore;

import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("isFilter")
    private String isFilter;

    @SerializedName("message")
    private String message;

    public Request(String isFilter, String message) {
        this.isFilter = isFilter;
        this.message = message;
    }

}
