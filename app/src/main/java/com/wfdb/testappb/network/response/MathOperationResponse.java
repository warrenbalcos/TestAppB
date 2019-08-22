package com.wfdb.testappb.network.response;

/**
 * Created by warren on 2019-08-22.
 */
public class MathOperationResponse {
    public long id;
    public float first;
    public float second;
    public float result;

    @Override
    public String toString() {
        return "MathOperationResponse{" +
                "id=" + id +
                ", first=" + first +
                ", second=" + second +
                ", result=" + result +
                '}';
    }
}
