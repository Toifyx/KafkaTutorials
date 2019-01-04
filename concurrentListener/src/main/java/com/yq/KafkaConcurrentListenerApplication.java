

package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Simple to Introduction
 * className: KafkaConcurrentListenerApplication
 *
 * @author EricYang
 * @version 2018/5/12 17:06
 */
@SpringBootApplication
public class KafkaConcurrentListenerApplication {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConcurrentListenerApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaConcurrentListenerApplication.class, args);
        logger.info("Done start Spring boot");

    }
}
