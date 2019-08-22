package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.EchoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by warren on 2019-08-21.
 */
public interface SimpleRestServerAPI {

    @GET("echo")
    Call<EchoResponse> echo(@Query("data") String data);

}
