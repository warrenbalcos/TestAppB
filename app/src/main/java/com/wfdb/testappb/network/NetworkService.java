package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.utils.Callback;

/**
 * Created by warren on 2019-08-22.
 */
public class NetworkService {

    private EchoNetworkProvider echoNetworkProvider;
    public static final NetworkService INSTANCE = new NetworkService();

    private NetworkService() {
    }

    public static synchronized NetworkService getInstance() {
        return INSTANCE;
    }

    public synchronized void setEchoNetworkProvider(EchoNetworkProvider echoNetworkProvider) {
        this.echoNetworkProvider = echoNetworkProvider;
    }

    public synchronized void echo(String data, Callback<EchoResponse> callback) {
        if (echoNetworkProvider == null) {
            callback.onFail(new Throwable("no echo api handler found"));
            return;
        }
        echoNetworkProvider.fetchEcho(data, callback);
    }

    public synchronized boolean hasEchoNetworkProvider() {
        return echoNetworkProvider != null;
    }

}
