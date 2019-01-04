#
http://127.0.0.1:5556/service/swagger-ui.html  wrong
http://127.0.0.1:5556/swagger-ui.html
http://127.0.0.1:5556/producer/hello?message=aaa
127.0.0.1:5556/swagger-ui.html#!/producer45controller/createDeviceJsonMsgUsingPOST

无法使用
The Spring-kafka 2.1 （ <version>2.1.2.RELEASE</version>） is based on the Spring Framework 5.0
springboot 1.5.12使用的是spring 4.3.x


如果是低版本的
Caused by: java.lang.NoClassDefFoundError: org/apache/kafka/common/header/Headers
	at org.springframework.kafka.core.KafkaTemplate.<init>(KafkaTemplate.java:71) ~[spring-kafka-1.3.5.RELEASE.jar:na]
	at org.springframework.kafka.core.KafkaTemplate.<init>(KafkaTemplate.java:83) ~[spring-kafka-1.3.5.RELEASE.jar:na]
	at org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration.kafkaTemplate(KafkaAutoConfiguration.java:57) ~[spring-boot-autoconfigure-1.5.12.RELEASE.jar:1.5.12.RELEASE]
	at org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration$$EnhancerBySpringCGLIB$$77efe360.CGLIB$kafkaTemplate$0(<generated>) ~[spring-boot-autoconfigure-1.5.12.RELEASE.jar:1.5.12.R
	        <dependency>
                <groupId>org.apache.kafka</groupId>
                <artifactId>kafka-clients</artifactId>
                <!--<version>0.11.0.2</version>-->
                <version>0.10.1.1</version>
            </dependency>