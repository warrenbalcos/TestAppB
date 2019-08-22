package com.wfdb.testappb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.wfdb.testappb.utils.Constants;

/**
 * Created by warren on 2019-08-21.
 */
public class MainBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = MainBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            logError(context, "null intent received");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            logError(context, "empty intent received");
            return;
        }
        String data = extras.getString(Constants.SUBMIT_ECHO_DATA);
        if (TextUtils.isEmpty(data)) {
            logError(context, "empty data received");
            return;
        }
        Log.d(TAG, data);
        EchoService.enqueueWork(context, data);
    }

    private void logError(Context context, String message) {
        Log.e(TAG, message);
    }
}
