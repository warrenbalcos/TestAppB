package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.network.response.MathOperationResponse;
import com.wfdb.testappb.utils.Callback;

/**
 * Created by warren on 2019-08-22.
 */
public class NetworkService {

    private EchoNetworkProvider echoNetworkProvider;
    private MathNetworkProvider.Add addProvider;
    private MathNetworkProvider.Subtract subtractProvider;
    private MathNetworkProvider.Multiply multiplyProvider;
    private MathNetworkProvider.Divide divideProvider;

    public static final NetworkService INSTANCE = new NetworkService();

    private NetworkService() {
    }

    public static synchronized NetworkService getInstance() {
        return INSTANCE;
    }

    public synchronized void setEchoNetworkProvider(EchoNetworkProvider echoNetworkProvider) {
        this.echoNetworkProvider = echoNetworkProvider;
    }

    public void setAddProvider(MathNetworkProvider.Add addProvider) {
        this.addProvider = addProvider;
    }

    public void setSubtractProvider(MathNetworkProvider.Subtract subtractProvider) {
        this.subtractProvider = subtractProvider;
    }

    public void setMultiplyProvider(MathNetworkProvider.Multiply multiplyProvider) {
        this.multiplyProvider = multiplyProvider;
    }

    public void setDivideProvider(MathNetworkProvider.Divide divideProvider) {
        this.divideProvider = divideProvider;
    }

    public synchronized void echo(String data, Callback<EchoResponse> callback) {
        if (echoNetworkProvider == null) {
            callback.onFail(new Throwable("no echo api handler found"));
            return;
        }
        echoNetworkProvider.fetchEcho(data, callback);
    }

    public synchronized void doAddition(float first, float second, Callback<MathOperationResponse> callback) {
        if (addProvider == null) {
            callback.onFail(new Throwable("no add api handler found"));
            return;
        }
        addProvider.doAddition(first, second, callback);
    }

    public synchronized void doSubtraction(float first, float second, Callback<MathOperationResponse> callback) {
        if (subtractProvider == null) {
            callback.onFail(new Throwable("no subtract api handler found"));
            return;
        }
        subtractProvider.doSubtraction(first, second, callback);
    }

    public synchronized void doMultiplication(float first, float second, Callback<MathOperationResponse> callback) {
        if (multiplyProvider == null) {
            callback.onFail(new Throwable("no multiply api handler found"));
            return;
        }
        multiplyProvider.doMultiplication(first, second, callback);
    }

    public synchronized void doDivision(float first, float second, Callback<MathOperationResponse> callback) {
        if (divideProvider == null) {
            callback.onFail(new Throwable("no divide api handler found"));
            return;
        }
        divideProvider.doDivision(first, second, callback);
    }

    public synchronized boolean hasEchoNetworkProvider() {
        return echoNetworkProvider != null;
    }

}
