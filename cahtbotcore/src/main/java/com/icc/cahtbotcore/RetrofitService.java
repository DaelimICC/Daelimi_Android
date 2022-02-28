package com.icc.cahtbotcore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("{path}")
    Call<ResponseAnswer> getResponse(@Path ("path") String path, @Body Request request);
}
