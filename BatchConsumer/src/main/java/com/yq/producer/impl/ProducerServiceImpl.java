package com.yq.producer.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @Autowired
    private KafkaTemplate template;

    @Override
    public void send(String topic, String str, int count) {
        for (int i=0; i < count; i++) {
            template.send(topic, str);
        }
    }

    @Override
    public void sendJson(String topic, String json, int count) {
        for (int i=0; i < count; i++) {
            sendJson(topic, json);
        }
    }

    //发送消息方法
    private void sendJson(String topic, String json) {
        JSONObject jsonObj = JSON.parseObject(json);

        jsonObj.put("topic", topic);
        jsonObj.put("ts", System.currentTimeMillis() + "");

        logger.info("json+++++++++++++++++++++  message = {}", jsonObj.toJSONString());

        ListenableFuture<SendResult<String, String>> future = template.send(topic, jsonObj.toJSONString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("msg OK. " + result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("msg send failed.", ex);
            }
        });
    }
}
