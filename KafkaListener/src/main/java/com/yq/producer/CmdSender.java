package com.yq.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.domain.CmdMessage;
import com.yq.domain.DeviceMessage;
import com.yq.domain.MsgData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by yqbjtu on 2018/4/22.
 */
@Component
@Slf4j
public class CmdSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        CmdMessage cmdMsg = new CmdMessage();
        cmdMsg.setId(System.currentTimeMillis());
        cmdMsg.setMsg(UUID.randomUUID().toString());
        cmdMsg.setSendTime(new Date());
        log.info("+++++++++++++++++++++ topic01  message = {}", gson.toJson(cmdMsg));
        kafkaTemplate.send("topic01", gson.toJson(cmdMsg));
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

        log.info("+++++++++++++++++++++  message = {}", gson.toJson(devMsg));
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
        kafkaTemplate.send(topic, gson.toJson(devMsg));
    }

    //发送消息方法
    public void sendJson(String topic, String json) {
        JSONObject jsonObj = JSON.parseObject(json);

        jsonObj.put("topic", topic);
        jsonObj.put("ts", System.currentTimeMillis() + "");

        log.info("json+++++++++++++++++++++  message = {}", jsonObj.toJSONString());


        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, jsonObj.toJSONString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("msg OK." + result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
             System.out.println("msg send failed: " + ex.getLocalizedMessage());
            }

        });
    }
}
