package com.wfdb.testappb;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.wfdb.testappb.enums.MathOperationEnum;
import com.wfdb.testappb.network.EchoNetworkProvider;
import com.wfdb.testappb.network.MathNetworkProvider;
import com.wfdb.testappb.network.NetworkService;
import com.wfdb.testappb.network.response.EchoResponse;
import com.wfdb.testappb.network.response.MathOperationResponse;
import com.wfdb.testappb.utils.Callback;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by warren on 2019-08-22.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class MathServiceTest {

    @Test
    public void addTest() {
        NetworkService.getInstance().setAddProvider(new MathNetworkProvider.Add() {
            @Override
            public void doAddition(float first, float second, Callback<MathOperationResponse> callback) {
                Assert.assertEquals(2, first, 2);
                Assert.assertEquals(3, second, 2);
            }
        });
        MathService.enqueueWork(ApplicationProvider.getApplicationContext(), 2, 3, MathOperationEnum.ADD);
    }

    @Test
    public void subtractTest() {
        NetworkService.getInstance().setSubtractProvider(new MathNetworkProvider.Subtract() {
            @Override
            public void doSubtraction(float first, float second, Callback<MathOperationResponse> callback) {
                Assert.assertEquals(3, first, 2);
                Assert.assertEquals(4, second, 2);
            }
        });
        MathService.enqueueWork(ApplicationProvider.getApplicationContext(), 3, 4, MathOperationEnum.SUBTRACT);
    }


    @Test
    public void multiplyTest() {
        NetworkService.getInstance().setMultiplyProvider(new MathNetworkProvider.Multiply() {
            @Override
            public void doMultiplication(float first, float second, Callback<MathOperationResponse> callback) {
                Assert.assertEquals(5, first, 2);
                Assert.assertEquals(6, second, 2);
            }
        });
        MathService.enqueueWork(ApplicationProvider.getApplicationContext(), 5, 6, MathOperationEnum.MULTIPLY);
    }

    @Test
    public void divideTest() {
        NetworkService.getInstance().setDivideProvider(new MathNetworkProvider.Divide() {
            @Override
            public void doDivision(float first, float second, Callback<MathOperationResponse> callback) {
                Assert.assertEquals(7, first, 2);
                Assert.assertEquals(8, second, 2);
            }
        });
        MathService.enqueueWork(ApplicationProvider.getApplicationContext(), 7, 8, MathOperationEnum.DIVIDE);
    }
}