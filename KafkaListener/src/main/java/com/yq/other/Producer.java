package com.yq.other;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Simple to Introduction
 * className: Producer
 *
 * @author yqbjtul
 * @version 2018/4/24 19:58
 */
public class Producer {
    public void test() {
        Properties props = new Properties();
        //9.x.y.z ubuntu01  如果不在hosts文件中配置这个，一直无法连接。 虚拟机在virtualBox中，选择的桥接器模式
        //这个demo在windows上，
        props.put("bootstrap.servers", "10.4.16.58:9092");
        //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
        props.put("acks", "all");
        //如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
        props.put("retries", 0);
        //The producer maintains buffers of unsent records for each partition.
        props.put("batch.size", 16384);
        //默认立即发送，这里这是延时毫秒数
        props.put("linger.ms", 1);
        //生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
        props.put("buffer.memory", 33554432);
        //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //创建kafka的生产者类
        // close();//Close this producer.
        // close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
        // flush() ;所有缓存记录被立刻发送
        KafkaProducer producer = new KafkaProducer<String, String>(props);
        int i = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        for (i = 0; i < 10; i++) {
            Date now = new Date();
            String key = format.format(now);
            producer.send(new ProducerRecord<String, String>("topic01", Integer.toString(i), key));
        }
        producer.flush();
        producer.close();
    }

    public static void main(String args[]) {
        Producer p = new Producer();
        p.test();
    }
}

