package com.yq.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by yqbjtu on 2018/4/22.
 */
@Data
public class CmdMessage {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

}