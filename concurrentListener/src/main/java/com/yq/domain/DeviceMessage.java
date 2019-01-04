
package com.yq.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Simple to Introduction
 * className: DeviceMessage
 *
 * @author yqbjtu
 * @version 2018/4/28 10:28
 */

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
}

 */
@Data
public class DeviceMessage {
    @ApiModelProperty(
            required = false,
            value = "msgId",
            example = "123456"
    )
    private Long id;    //id

    @ApiModelProperty(
            required = true,
            value = "deviceId",
            example = "9a7b3d04"
    )
    private String deviceid;

    @ApiModelProperty(
            required = true,
            value = "topic",
            example = "topic01"
    )
    private String topic;

    private MsgData msg; //消息

    @ApiModelProperty(
            required = false,
            value = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            example = "2018-05-02T15:18:50.632"
    )
    //standard forms ("yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    // "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"))
    private Date sendTime;  //时间戳
    private String ts;  //时间戳
}
