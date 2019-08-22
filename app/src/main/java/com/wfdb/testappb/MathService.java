package com.wfdb.testappb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.wfdb.testappb.enums.MathOperationEnum;
import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.response.MathOperationResponse;
import com.wfdb.testappb.utils.Callback;
import com.wfdb.testappb.utils.Constants;

/**
 * Created by warren on 2019-08-22.
 */
public class MathService extends JobIntentService {

    private static final String TAG = MathService.class.getSimpleName();

    public static final int JOB_ID = 2;

    public static void enqueueWork(Context context, float first, float second, MathOperationEnum operation) {
        Intent intent = new Intent();
        intent.putExtra(Constants.FIRST, first);
        intent.putExtra(Constants.SECOND, second);
        intent.putExtra(Constants.OPERATION, operation.name());
        enqueueWork(context, MathService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "handle work");
        final float first = intent.getFloatExtra(Constants.FIRST, 0f);
        final float second = intent.getFloatExtra(Constants.SECOND, 0f);

        final MathOperationEnum op;
        try {
            op = MathOperationEnum.valueOf(intent.getStringExtra(Constants.OPERATION));
        } catch (IllegalArgumentException e) {
            broadcastError(getString(R.string.empty_data_error_message), e);
            return;
        }

        Callback<MathOperationResponse> callback = new Callback<MathOperationResponse>() {

            @Override
            public void onSuccess(MathOperationResponse response) {
                Log.d(TAG, "response -> " + response);
                broadcastResponse(response, op);
            }

            @Override
            public void onFail(Throwable e) {
                broadcastError(getString(R.string.remote_api_failed_error_message), e);
            }
        };

        switch (op) {
            case ADD:
                NetworkService.getInstance().doAddition(first, second, callback);
                break;
            case MULTIPLY:
                NetworkService.getInstance().doMultiplication(first, second, callback);
                break;
            case DIVIDE:
                NetworkService.getInstance().doDivision(first, second, callback);
                break;
            case SUBTRACT:
                NetworkService.getInstance().doSubtraction(first, second, callback);
                break;
        }
    }

    private void broadcastResponse(MathOperationResponse data, MathOperationEnum operation) {
        Intent response = new Intent();
        response.setAction(Constants.MATH_OPERATION_RESPONSE_ACTION);
        response.putExtra(Constants.FIRST, data.first);
        response.putExtra(Constants.SECOND, data.second);
        response.putExtra(Constants.RESULT, data.result);
        response.putExtra(Constants.OPERATION, operation.name());
        sendBroadcast(response);
    }

    private void broadcastError(String message, Throwable t) {
        Log.e(TAG, message, t);
        Intent response = new Intent();
        response.setAction(Constants.MATH_ERROR_ACTION);
        response.putExtra(Constants.ERROR_MESSAGE, message + ": " + t.getMessage());
        sendBroadcast(response);
    }
}
