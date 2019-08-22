package com.wfdb.testappb;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.wfdb.testappb.config.Config;
import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by warren on 2019-08-21.
 */
public class EchoService extends JobIntentService {

    public static final int JOB_ID = 1;

    private static final String TAG = EchoService.class.getSimpleName();

    public static void enqueueWork(Context context, String data) {
        Intent intent = new Intent();
        intent.putExtra(Constants.SUBMIT_ECHO_DATA, data);
        enqueueWork(context, EchoService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "handle work");
        final String data = intent.getStringExtra(Constants.SUBMIT_ECHO_DATA);
        if (TextUtils.isEmpty(data)) {
            broadcastError(getString(R.string.empty_data_error_message));
            return;
        }
        createNetworkService().echo(data).enqueue(new Callback<EchoResponse>() {
            @Override
            public void onResponse(Call<EchoResponse> call, Response<EchoResponse> response) {
                EchoResponse body = response.body();
                Log.d(TAG, "response -> " + body);
                if (body == null) {
                    broadcastError(getString(R.string.unexpected_server_respose_error_message));
                } else {
                    broadcastResponse(body);
                }
            }

            @Override
            public void onFailure(Call<EchoResponse> call, Throwable t) {
                broadcastError(getString(R.string.remote_api_failed_error_message), t);
            }
        });
    }

    private NetworkService createNetworkService() {
        return new Retrofit.Builder()
                .baseUrl(Config.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkService.class);
    }

    private void broadcastResponse(EchoResponse data) {
        Intent response = new Intent();
        response.setAction(Constants.ECHO_RESPONSE_ACTION);
        response.putExtra(Constants.ECHO_CONTENT, data.content);
        response.putExtra(Constants.ECHO_COUNT, data.count);
        sendBroadcast(response);
    }

    private void broadcastError(String message) {
        Log.e(TAG, message);
        Intent response = new Intent();
        response.setAction(Constants.ECHO_ERROR_ACTION);
        response.putExtra(Constants.ERROR_MESSAGE, message);
        sendBroadcast(response);
    }

    private void broadcastError(String message, Throwable t) {
        Log.e(TAG, message, t);
        Intent response = new Intent();
        response.setAction(Constants.ECHO_ERROR_ACTION);
        response.putExtra(Constants.ERROR_MESSAGE, message + ": " + t.getMessage());
        sendBroadcast(response);
    }
}
