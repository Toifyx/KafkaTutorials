/*
 * Copyright (C) 2018 org.citic.iiot, Inc. All Rights Reserved.
 */


package com.yq.other;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple to Introduction
 * className: JsonDemo
 *
 * @author EricYang
 * @version 2018/5/22 17:26
 */
public class JsonDemo {

    public static void main(String args[]) {
        Map paramMap = new HashMap<String, Object>();
        paramMap.put("key1", "value02");
        paramMap.put("isControl", 1);
        try {
            String isControl = (String) paramMap.get("isControl");
            System.out.println("isControl:" + isControl + " isControl1:" + paramMap.get("isControl"));
        }
        catch (Exception ex) {
            System.out.println("isControl:" + ex.getLocalizedMessage() + " isControl1:" + ex.getMessage());
        }
        Map<String, Object> paramMap2 = new HashMap<String, Object>();
        paramMap2.putAll(Collections.unmodifiableMap(paramMap));
        //Set<Map.Entry> entries = paramMap2.entrySet();
        for (String appIdConnectorId : paramMap2.keySet()) {
            String status = (String)paramMap2.get(appIdConnectorId);
            System.out.println("status:" + status);
        }


    }


}
