package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.yq.producer.ProducerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Simple to Introduction
 * className: ProducerController
 *
 * @author yqbjtu
 * @version 2018/4/27 8:57
 */
@RestController
@RequestMapping(value = "/producer")
public class ProducerController {
    @Autowired
    ProducerService producerService;

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @ApiOperation(value = "send")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic02", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "content", required = true, dataType = "string", paramType = "query")
    })
    @PostMapping(value = "/send", produces = "application/json;charset=UTF-8")
    public String sendMsg(@RequestParam  String topic, @RequestParam String content) {
        logger.info("enter sendMsg, topic={}, content={}", topic, content);
        producerService.send(topic, content, 1);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "devMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic02", defaultValue = "topic02", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "count", value = "发送多少遍", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "deviceId", value = "deviceId", defaultValue = "0bcfff",  required = true, dataType = "string", paramType = "query")
    })
    @PostMapping(value = "/devMsg", produces = "application/json;charset=UTF-8")
    public String createDeviceMsg(@RequestParam  String topic, @RequestParam int count, @RequestParam String deviceId) {
        producerService.send(topic, deviceId, count);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "devJsonMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic", defaultValue = "topic02", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "count", value = "发送多少遍", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "jsonStr", defaultValue = "jsonStr",  required = true, dataType = "DeviceMessage", paramType = "body")
    })
    @PostMapping(value = "/devJsonMsg", produces = "application/json;charset=UTF-8")
    public String createDeviceJsonMsg(@RequestParam String topic, @RequestParam int count, @RequestBody String jsonStr) {
        producerService.sendJson(topic, jsonStr, count);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }
}
