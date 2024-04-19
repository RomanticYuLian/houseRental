package com.yulian.houserental.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean checkPrice(String price) {
        Pattern pattern = Pattern
                .compile("(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)");
        Matcher matcher = pattern.matcher(price);

        return matcher.matches();
    }

    public static boolean checkPassword(String s) {
        Pattern pattern = Pattern
                .compile("^(?:[a-zA-Z0-9]{5,16})$");
        Matcher matcher = pattern.matcher(s);

        return matcher.matches();
    }

    public static Boolean isImage(String filename) {
        String type = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        return type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png") || type.equals(".gif");
    }

}
