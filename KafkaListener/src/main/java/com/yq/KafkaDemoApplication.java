package com.yq;

import com.yq.producer.CmdSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yqbjtu on 2018/4/22.
 */
@SpringBootApplication
public class KafkaDemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaDemoApplication.class, args);
        logger.info("Done start Spring boot");
    }
}
