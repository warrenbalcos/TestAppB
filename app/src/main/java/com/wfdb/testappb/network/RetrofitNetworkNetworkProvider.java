package com.wfdb.testappb.network;

import com.wfdb.testappb.config.Config;
import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.network.response.MathOperationResponse;
import com.wfdb.testappb.utils.Callback;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by warren on 2019-08-22.
 */
public class RetrofitNetworkNetworkProvider implements
        EchoNetworkProvider,
        MathNetworkProvider.Add,
        MathNetworkProvider.Subtract,
        MathNetworkProvider.Multiply,
        MathNetworkProvider.Divide {

    private Retrofit retrofit;
    private String baseUrl;

    public RetrofitNetworkNetworkProvider(String baseUrl) {
        setBaseUrl(baseUrl);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Config.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void fetchEcho(String data, final Callback<EchoResponse> callback) {
        retrofit.create(SimpleRestServerAPI.class)
                .echo(data)
                .enqueue(new retrofit2.Callback<EchoResponse>() {
                    @Override
                    public void onResponse(Call<EchoResponse> call, Response<EchoResponse> response) {
                        EchoResponse body = response.body();
                        if (body == null) {
                            callback.onFail(new Throwable("unexpected response from the server"));
                        } else {
                            callback.onSuccess(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<EchoResponse> call, Throwable t) {
                        callback.onFail(t);
                    }
                });
    }

    @Override
    public void doAddition(float first, float second, final Callback<MathOperationResponse> callback) {
        retrofit.create(SimpleRestServerAPI.class)
                .add(first, second)
                .enqueue(new retrofit2.Callback<MathOperationResponse>() {
                    @Override
                    public void onResponse(Call<MathOperationResponse> call, Response<MathOperationResponse> response) {
                        MathOperationResponse body = response.body();
                        if (body == null) {
                            callback.onFail(new Throwable("unexpected response from the server"));
                        } else {
                            callback.onSuccess(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<MathOperationResponse> call, Throwable t) {
                        callback.onFail(t);
                    }
                });
    }

    @Override
    public void doSubtraction(float first, float second, final Callback<MathOperationResponse> callback) {
        retrofit.create(SimpleRestServerAPI.class)
                .subtract(first, second)
                .enqueue(new retrofit2.Callback<MathOperationResponse>() {
                    @Override
                    public void onResponse(Call<MathOperationResponse> call, Response<MathOperationResponse> response) {
                        MathOperationResponse body = response.body();
                        if (body == null) {
                            callback.onFail(new Throwable("unexpected response from the server"));
                        } else {
                            callback.onSuccess(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<MathOperationResponse> call, Throwable t) {
                        callback.onFail(t);
                    }
                });
    }

    @Override
    public void doMultiplication(float first, float second, final Callback<MathOperationResponse> callback) {
        retrofit.create(SimpleRestServerAPI.class)
                .multiply(first, second)
                .enqueue(new retrofit2.Callback<MathOperationResponse>() {
                    @Override
                    public void onResponse(Call<MathOperationResponse> call, Response<MathOperationResponse> response) {
                        MathOperationResponse body = response.body();
                        if (body == null) {
                            callback.onFail(new Throwable("unexpected response from the server"));
                        } else {
                            callback.onSuccess(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<MathOperationResponse> call, Throwable t) {
                        callback.onFail(t);
                    }
                });
    }

    @Override
    public void doDivision(float first, float second, final Callback<MathOperationResponse> callback) {
        retrofit.create(SimpleRestServerAPI.class)
                .divide(first, second)
                .enqueue(new retrofit2.Callback<MathOperationResponse>() {
                    @Override
                    public void onResponse(Call<MathOperationResponse> call, Response<MathOperationResponse> response) {
                        MathOperationResponse body = response.body();
                        if (body == null) {
                            callback.onFail(new Throwable("unexpected response from the server"));
                        } else {
                            callback.onSuccess(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<MathOperationResponse> call, Throwable t) {
                        callback.onFail(t);
                    }
                });
    }

}
