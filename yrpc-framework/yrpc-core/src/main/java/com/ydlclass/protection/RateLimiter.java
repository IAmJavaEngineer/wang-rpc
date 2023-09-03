package com.ydlclass.protection;

/**
 * @author it楠老师
 * @createTime 2023-07-25
 */
public interface RateLimiter {
    
    /**
     * 是否允许新的请求进入
     * @return true 可以进入  false  拦截
     */
    boolean allowRequest();
}
