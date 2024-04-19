package com.yulian.houserental.utils;

import java.net.URLDecoder;

//该工具类用于解决postman测试时RequestBody中的中文编码问题以及请求到的参数为keyword=
public class DecodeUtils {
    private static final int isEntryStyle = 1;
    public static String newCode(String s) {
        if (s.length()<9){
            return null;
        }
        String s1 = s.split("=")[1];
        return URLDecoder.decode(s1);
    }
}
