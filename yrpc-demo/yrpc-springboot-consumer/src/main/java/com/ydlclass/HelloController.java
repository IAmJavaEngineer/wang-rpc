package com.ydlclass;

import com.ydlclass.annotation.YrpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author it楠老师
 * @createTime 2023-07-30
 */
@RestController
public class HelloController {
    
    // 需要注入一个代理对象
    @YrpcService
    private HelloYrpc helloYrpc;
    
    @GetMapping("hello")
    public String hello(){
        return helloYrpc.sayHi("provider");
    }
    
}
