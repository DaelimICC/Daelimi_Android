package com.icc.daelimi;

/**
 *  통신을 위한 인터페이스
 *  api서버에 post방식으로 Request 객체를 바디에 넣어 보냄.
 *  응답 결과를 ResponseAnswer 객체로 돌려받음.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("api/")
    Call<ResponseAnswer> getResponse(@Body Request request);
}
