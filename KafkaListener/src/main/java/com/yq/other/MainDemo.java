
package com.yq.other;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple to Introduction
 * className: MainDemo
 *
 * @author yqbjtu
 * @version 2018/4/27 18:58
 */
public class MainDemo {
    public static void main(String args[]) {
        String keyStr = "aaa_b20170428";
        String dateStr = null;
        if (keyStr != null && keyStr.length() > 8) {
            int length = keyStr.length();
            dateStr = keyStr.substring(length - 8);
        }
        System.out.println("dateStr:" + dateStr);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date now = new Date();
        String str = format.format(now);
        System.out.println("now time is " + str);

        long nowLong = System.currentTimeMillis();
        System.out.println("now time is " + nowLong);
    }
}
