package com.yq.producer.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.KafkaConcurrentListenerApplication;
import com.yq.domain.DeviceMessage;
import com.yq.domain.MsgData;
import com.yq.producer.ProducerService;
import com.yq.producer.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.HashMap;

/**
 * Simple to Introduction
 * className: ProducerServiceImpl
 *
 * @author yqbjtu
 * @version 2018/4/27 8:52
 */
@Service
public class ProducerServiceImpl implements ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private KafkaTemplate template;

    @Override
    public void send(String topic, String str, int count) {
        for (int i=0; i < count; i++) {
            send(topic, str);
        }
    }

    @Override
    public void sendJson(String topic, String json, int count) {
        for (int i=0; i < count; i++) {
            sendJson(topic, json);
        }
    }

    //发送消息方法
    public void send(String topic, String deviceId) {
        DeviceMessage devMsg = new DeviceMessage();
        devMsg.setId(System.currentTimeMillis());

        devMsg.setSendTime(new Date());
        devMsg.setDeviceid(deviceId);
        devMsg.setTs(System.currentTimeMillis() + "");

        MsgData msgData = new MsgData();
        HashMap<String, String> map = new HashMap<>();
        map.put("L0001", "50");
        map.put("L0003", "8");
        map.put("L0002", "81");
        map.put("Part1_temperature", "1804");
        msgData.setData(map);
        msgData.setDelta(0L);
        devMsg.setMsg(msgData);
        devMsg.setTopic(topic);

        logger.info("+++++++++++++++++++++  message = {}", gson.toJson(devMsg));
        /*
{
    "msg": {
        "data": {
            "A0001": "50",
            "B0003": "8",
            "C0002": "81",
            "Zone1_temperature": "1804"
        },
        "delta": 0
    },
    "deviceid": "86b874260d224cf8870bef1df60bcfff",
    "ts": "1524736016604"
}*/
        template.send(topic, gson.toJson(devMsg));
    }

    //发送消息方法
    public void sendJson(String topic, String json) {
        JSONObject jsonObj = JSON.parseObject(json);

        jsonObj.put("topic", topic);
        jsonObj.put("ts", System.currentTimeMillis() + "");

        logger.info("json+++++++++++++++++++++  message = {}", jsonObj.toJSONString());

        ListenableFuture<SendResult<String, String>> future = template.send(topic, jsonObj.toJSONString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("msg OK." + result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("msg send failed: ");
            }
        });
    }
}
