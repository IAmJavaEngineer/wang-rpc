package com.ydlclass.proxy;

import com.ydlclass.ReferenceConfig;
import com.ydlclass.YrpcBootstrap;
import com.ydlclass.discovery.RegistryConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author it楠老师
 * @createTime 2023-07-30
 */
public class YrpcProxyFactory {
    
    private static Map<Class<?>,Object> cache = new ConcurrentHashMap<>(32);
    
    public static <T> T getProxy(Class<T> clazz) {
    
        Object bean = cache.get(clazz);
        if(bean != null){
            return (T)bean;
        }
    
        ReferenceConfig<T> reference = new ReferenceConfig<>();
        reference.setInterface(clazz);
        
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
        T t = reference.get();
        cache.put(clazz,t);
        return t;
    }
}
