/*
 * Copyright (C) 2018 org.citic.iiot, Inc. All Rights Reserved.
 */


package com.yq.other;


import com.yq.KafkaDemoApplication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Simple to Introduction
 * className: MyTimeFormat
 *
 * @author EricYang
 * @version 2018/5/15 16:25
 */
public class MyTimeFormat {
    private static final Logger logger = LoggerFactory.getLogger(MyTimeFormat.class);

    public static void main(String args[]) {
        String formatStr = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        TimeZone tz1 = TimeZone.getTimeZone("GMT+8");
        SimpleDateFormat format = new SimpleDateFormat(formatStr);


        TimeZone tz = format.getTimeZone();
        System.out.println("SimpleDateFormat default timezone:" + tz.getDisplayName());
        Date date = new Date();
        String strDate = format.format(date);

        format.setTimeZone(tz1);
        String strDate2 = format.format(date);
        System.out.println("strDate1:" + strDate);
        System.out.println("strDate2:" + strDate2);



        String strDate1 = "2018-05-13T07:53:59.863";
        try {
            Date date1 = format.parse(strDate1);
            System.out.println("date1:" + date1);
        }
        catch (Exception ex) {
            System.out.println("ex:" + ex.getLocalizedMessage());
        }

    }


    private Date convertToDate(String dateStr) {
        //DateUtil.parseDate
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        TimeZone tz = TimeZone.getTimeZone("GMT+8");
        format.setTimeZone(tz);
        Date date = null;

        if (StringUtils.isNotBlank(dateStr)) {
            try {
                date = format.parse(dateStr.trim());
            } catch (ParseException ex) {
                logger.warn("Can't parse dateStr={} with format=", dateStr, pattern);
            }
        }

        return date;
    }
}
