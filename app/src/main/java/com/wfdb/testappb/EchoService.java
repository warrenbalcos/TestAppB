package com.wfdb.testappb;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.utils.Callback;
import com.wfdb.testappb.utils.Constants;

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
        NetworkService.getInstance().echo(data, new Callback<EchoResponse>() {
            @Override
            public void onSuccess(EchoResponse response) {
                Log.d(TAG, "response -> " + response);
                broadcastResponse(response);
            }

            @Override
            public void onFail(Throwable e) {
                broadcastError(getString(R.string.remote_api_failed_error_message), e);
            }
        });
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
