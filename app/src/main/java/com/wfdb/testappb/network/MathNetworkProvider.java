package com.wfdb.testappb.network;

import com.wfdb.testappb.network.response.MathOperationResponse;
import com.wfdb.testappb.utils.Callback;

/**
 * Created by warren on 2019-08-22.
 */
public interface MathNetworkProvider {

    interface Add {
        void doAddition(float first, float second, Callback<MathOperationResponse> callback);
    }

    interface Subtract {
        void doSubtraction(float first, float second, Callback<MathOperationResponse> callback);
    }

    interface Multiply {
        void doMultiplication(float first, float second, Callback<MathOperationResponse> callback);
    }

    interface Divide {
        void doDivision(float first, float second, Callback<MathOperationResponse> callback);
    }
}
