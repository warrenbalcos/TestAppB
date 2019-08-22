package com.wfdb.testappb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.wfdb.testappb.config.Config;
import com.wfdb.testappb.enums.MathOperationEnum;
import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.RetrofitNetworkNetworkProvider;
import com.wfdb.testappb.utils.Constants;

/**
 * Created by warren on 2019-08-21.
 */
public class MainBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = MainBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.e(TAG, "null intent received");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.e(TAG, "empty intent received");
            return;
        }

        if (!NetworkService.getInstance().hasEchoNetworkProvider()) {
            RetrofitNetworkNetworkProvider provider = new RetrofitNetworkNetworkProvider(Config.getInstance().getBaseUrl());
            NetworkService.getInstance().setEchoNetworkProvider(provider);
            NetworkService.getInstance().setAddProvider(provider);
            NetworkService.getInstance().setSubtractProvider(provider);
            NetworkService.getInstance().setMultiplyProvider(provider);
            NetworkService.getInstance().setDivideProvider(provider);
        }
        if (Constants.SUBMIT_ECHO_ACTION.equals(intent.getAction())) {
            handleEchoRequest(context, extras);
        } else if (Constants.SUBMIT_MATH_OPERATION_ACTION.equals(intent.getAction())) {
            handleMathRequest(context, extras);
        }
    }

    private void handleEchoRequest(Context context, Bundle extras) {
        String data = extras.getString(Constants.SUBMIT_ECHO_DATA);
        if (TextUtils.isEmpty(data)) {
            Log.e(TAG, "empty data received");
            return;
        }
        Log.d(TAG, data);
        EchoService.enqueueWork(context, data);
    }

    private void handleMathRequest(Context context, Bundle extras) {
        float first = extras.getFloat(Constants.FIRST);
        float second = extras.getFloat(Constants.SECOND);
        MathOperationEnum op;
        try {
            op = MathOperationEnum.valueOf(extras.getString(Constants.OPERATION));
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "empty data received", e);
            return;
        }
        MathService.enqueueWork(context, first, second, op);
    }
}
