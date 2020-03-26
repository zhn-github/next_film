package com.next.zhn.film.controller.common;


import com.next.zhn.film.controller.auth.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

public final class TraceUtil {

    private TraceUtil(){}

    private static ThreadLocal<String>  threadLocal = new ThreadLocal<String>();

    public static void initThred(String uuid){
        threadLocal.set(uuid);
    }

    public static String getUserId(){
        return threadLocal.get();
    };


}