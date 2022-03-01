package com.icc.daelimi;

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
