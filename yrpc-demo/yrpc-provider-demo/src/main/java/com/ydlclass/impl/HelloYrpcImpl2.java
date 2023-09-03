package com.ydlclass.impl;

import com.ydlclass.HelloYrpc2;
import com.ydlclass.annotation.YrpcApi;

/**
 * @author it楠老师
 * @createTime 2023-06-27
 */
@YrpcApi
public class HelloYrpcImpl2 implements HelloYrpc2 {
    @Override
    public String sayHi(String msg) {
        return "hi consumer:" + msg;
    }
}
