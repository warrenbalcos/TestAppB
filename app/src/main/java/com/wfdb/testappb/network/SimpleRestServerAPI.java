package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.network.response.MathOperationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by warren on 2019-08-21.
 */
public interface SimpleRestServerAPI {

    @GET("echo")
    Call<EchoResponse> echo(@Query("data") String data);

    @GET("add")
    Call<MathOperationResponse> add(@Query("first") float first, @Query("second") float second);

    @GET("subtract")
    Call<MathOperationResponse> subtract(@Query("first") float first, @Query("second") float second);

    @GET("multiply")
    Call<MathOperationResponse> multiply(@Query("first") float first, @Query("second") float second);

    @GET("divide")
    Call<MathOperationResponse> divide(@Query("first") float first, @Query("second") float second);

}
