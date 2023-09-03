package com.ydlclass.impl;

import com.ydlclass.HelloYrpc;
import com.ydlclass.annotation.YrpcApi;

/**
 * @author it楠老师
 * @createTime 2023-06-27
 */
@YrpcApi(group = "primary")
public class HelloYrpcImpl implements HelloYrpc {
    @Override
    public String sayHi(String msg) {
        return "hi consumer:" + msg;
    }
}
