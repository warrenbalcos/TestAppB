package com.wfdb.testappb;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.wfdb.testappb.network.EchoNetworkProvider;
import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.utils.Callback;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by warren on 2019-08-22.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class EchoServiceTest {

    @Test
    public void onEchoNetworkTest() {
        NetworkService.getInstance().setEchoNetworkProvider(new EchoNetworkProvider() {
            @Override
            public void fetchEcho(String data, Callback<EchoResponse> callback) {
                EchoResponse echoResponse = new EchoResponse();
                echoResponse.content = data;
                echoResponse.count = 1;
                Assert.assertEquals("content", data);
                callback.onSuccess(echoResponse);
            }
        });
        EchoService.enqueueWork(ApplicationProvider.getApplicationContext(), "content");
    }
}