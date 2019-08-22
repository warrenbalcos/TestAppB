package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.utils.Callback;

/**
 * Created by warren on 2019-08-22.
 */
public interface EchoNetworkProvider {
    void fetchEcho(String data, Callback<EchoResponse> callback);
}
