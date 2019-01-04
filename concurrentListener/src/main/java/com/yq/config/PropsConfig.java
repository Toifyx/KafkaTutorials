
package com.yq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Simple to Introduction
 * className: PropsConfig
 *
 * @author EricYang
 * @version 2018/5/12 18:39
 */

@Configuration
@Data
public class PropsConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String broker;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String enableAutoCommit;

}
