package com.yq;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.config.SaslConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import static org.slf4j.Logger.*;

/**
 * Simple to Introduction
 * className: SendMessageMain
 *
 * @author EricYang
 * @version 2018/7/10 11:30
 */
public class SendMessageMain {
    private static final Logger logger = LoggerFactory.getLogger(SendMessageMain.class);
    public static void main(String... args) throws Exception {
        try {

//            try {
//                Configuration configuration = Configuration.getConfiguration();
//                AppConfigurationEntry configurationEntries[] = configuration.getAppConfigurationEntry("org.apache.kafka.common.security.plain.PlainLoginModule");
//                //configuration.
//                if (configurationEntries == null) {
//                    String errorMessage = "Could not find a entry in this configuration: Server cannot start.";
//                    logger.error(errorMessage);
//                    throw new IOException(errorMessage);
//                }
//
//                for(AppConfigurationEntry entry: configurationEntries) {
//                    Map<String, ?> options = entry.getOptions();
//                    // Populate DIGEST-MD5 user -> password map with JAAS configuration entries from the "Server" section.
//                    // Usernames are distinguished from other options by prefixing the username with a "user_" prefix.
//                    for (Map.Entry<String, ?> pair : options.entrySet()) {
//                        String key = pair.getKey();
//                        String value = (String)pair.getValue();
//                        System.out.println("key:" + key + ", value:" + value);
//                    }
//                }
//            } catch (Exception exe) {
//                    System.out.println("exe:" + exe.getMessage());
//            }

            Properties props = new Properties();
            props.put("bootstrap.servers", "yamgqian-PC:9092");
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "ubuntu:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

//            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            String username = "producer";
            String password = "prod-sec";
            String jaasConfig = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+ username +"\" password=\"" + password + "\";";
//            props.put("sasl.jaas.config",
//                    "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"producer\" password=\"prod-sec\";");
//            props.put("sasl.jaas.config", jaasConfig);


            //sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="(username)" password="(password)";
            //System.setProperty("java.security.auth.login.config", "D:\\E\\workspaceGitub\\kafka_client_jaas.conf"); //配置文件路径

            //"security.protocol", ConfigDef.Type.STRING, "PLAINTEXT

            System.out.println("create KafkaProducer");
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            //String data = JSON.toJSONString("{\"aa\":\"bbb\"}");
            String data = "aaa";

            System.out.println("send data");

            ProducerRecord<String, String> producerRecord = new ProducerRecord("lizhenjun", data);
            //Future<RecordMetadata> future = producer.send(producerRecord);

            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                producer.send(producerRecord,
                        new org.apache.kafka.clients.producer.Callback() {
                            @Override
                            public void onCompletion(RecordMetadata metadata, Exception e) {
                                if(e != null) {
                                    System.out.println("onCompletion exception");
                                    e.printStackTrace();
                                }
                                System.out.println("The offset of the record we just sent is: " + metadata);
                            }
                        });
                System.out.println("flush producer");
                producer.flush();
            }



            System.out.println("close producer");
            producer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("when calling kafka output error." + ex.getMessage());
        }
    }

}
