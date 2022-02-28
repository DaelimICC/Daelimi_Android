package com.icc.daelimi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("function-1/")
    Call<ResponseAnswer> getResponse(@Body Request request);
}
