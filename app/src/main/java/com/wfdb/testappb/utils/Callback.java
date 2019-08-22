package com.wfdb.testappb.utils;

/**
 * Created by warren on 2019-08-22.
 */
public interface Callback<T> {

    void onSuccess(T response);

    void onFail(Throwable e);
}
