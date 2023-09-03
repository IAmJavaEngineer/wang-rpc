package com.ydlclass;

import com.ydlclass.discovery.RegistryConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author it楠老师
 * @createTime 2023-06-28
 */
@Slf4j
public class ConsumerApplication {
    
    public static void main(String[] args) {
        // 想尽一切办法获取代理对象,使用ReferenceConfig进行封装
        // reference一定用生成代理的模板方法，get()
        ReferenceConfig<HelloYrpc> reference = new ReferenceConfig<>();
        reference.setInterface(HelloYrpc.class);
        
        // 代理做了些什么?
        // 1、连接注册中心
        // 2、拉取服务列表
        // 3、选择一个服务并建立连接
        // 4、发送请求，携带一些信息（接口名，参数列表，方法的名字），获得结果
        YrpcBootstrap.getInstance()
            .application("first-yrpc-consumer")
            .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
            .serialize("hessian")
            .compress("gzip")
            .group("primary")
            .reference(reference);
    
        System.out.println("++------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        HelloYrpc helloYrpc = reference.get();
     
        while (true) {
//            try {
//                Thread.sleep(10000);
//                System.out.println("++------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            for (int i = 0; i < 50; i++) {
                String sayHi = helloYrpc.sayHi("你好yrpc");
                log.info("sayHi-->{}", sayHi);
            }
        }
        
    }
}
