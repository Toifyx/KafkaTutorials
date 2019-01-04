
package com.yq.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: MsgData
 *
 * @author yqbjtu
 * @version 2018/4/28 10:31
 */

/*
"data": {
            "L0001": "50",
            "L0003": "8",
            "L0002": "81",
            "Part1_temperature": "1804"
        },
        "delta": 0
 */
@Data
public class MsgData {
    @ApiModelProperty(
            required = true,
            value = "data",
            example = "{\"a1\":\"a1Value\", \"b1\":\"b1Value\"}"
    )
    private Map<String, String> data;
    @ApiModelProperty(
            required = true,
            value = "delta",
            example = "1"
    )
    private long delta;
}
