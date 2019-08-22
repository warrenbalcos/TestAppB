package com.wfdb.testappb.network.response;

/**
 * Created by warren on 2019-08-21.
 */
public class EchoResponse {
    public long count;
    public String content;

    @Override
    public String toString() {
        return "EchoResponse{" +
                "count=" + count +
                ", content='" + content + '\'' +
                '}';
    }
}
