
package com.yq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

/**
 * Simple to Introduction
 * className: MyListener
 *
 * @author EricYang
 * @version 2018/5/12 18:33
 */

@Slf4j
public class MyListener {
    private static final String TPOIC = "topic02";
    //CountDownLatch纯粹是为了测试方便，实际中完全可以不用
    public CountDownLatch countDownLatch0 = new CountDownLatch(3);
    public CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public CountDownLatch countDownLatch2 = new CountDownLatch(3);

    //@KafkaListener(topicPattern = "iiot.1prod.*", groupId = "group1")  这种方式也行
    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = TPOIC, partitions = { "0" }) })
    public void listenPartition0(ConsumerRecord<?, ?> record) {
        System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println("Received: " + record);
        if (kafkaMessage.isPresent()) {
            Object message = record.value();
            String topic = record.topic();
            log.info("----------------- record =topic："  + topic+ ", " + record);
        }
        countDownLatch0.countDown();
    }

    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = TPOIC, partitions = { "1" }) })
    public void listenPartition1(ConsumerRecord<?, ?> record) {
        System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println("Received: " + record);
        if (kafkaMessage.isPresent()) {
            Object message = record.value();
            String topic = record.topic();
            log.info("----------------- record =topic："  + topic+ ", " + record);
        }
        countDownLatch1.countDown();
    }

    @KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = TPOIC, partitions = { "2" }) })
    public void listenPartition2(ConsumerRecord<?, ?> record) {
        System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println("Received: " + record);
        if (kafkaMessage.isPresent()) {
            Object message = record.value();
            String topic = record.topic();
            log.info("----------------- record =topic："  + topic+ ", " + record);
        }
        countDownLatch2.countDown();
    }
}
