package com.yq.service.impl;

import com.yq.producer.CmdSender;
import com.yq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple to Introduction
 * className: ProducerServiceImpl
 *
 * @author yqbjtu
 * @version 2018/4/27 8:52
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private CmdSender sender;

    @Override
    public void send(String topic, String str, int count) {
        for (int i=0; i < count; i++) {
            sender.send(topic, str);
        }
    }

    @Override
    public void sendJson(String topic, String json, int count) {
        for (int i=0; i < count; i++) {
            sender.sendJson(topic, json);
        }
    }


}
