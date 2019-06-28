package com.xlx.kafka.client;


import com.xlx.common.KafkaConstans;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/28 20:20
 * @Description:
 */

public class KafkaConsumerClient {

    private Logger log = LoggerFactory.getLogger(KafkaConsumerClient.class);

    public KafkaConsumer getKafkaConsumer(String groupId) {

        KafkaConsumer<String, String> consumer = null;
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(KafkaConstans.KAFKA_PROPERTIES);
            properties.load(inputStream);
            if (properties != null) {
                try{
                    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
                    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
                    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
                    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
                    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
                    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
                    consumer = new KafkaConsumer<String, String>(properties);
                }catch (Throwable e){
                    log.warn("fail to init kafkaconsumer ---" + e);
                }

            } else {
                log.warn("fail to load kafkaConfig.properties");
                throw new Throwable("fail load inputstream");
            }
        } catch (Throwable e) {
            log.warn("fail to get kafkaConfig.properties -- " + e);
        }


        return consumer;


    }

    public void close(Consumer consumer) {

        if (consumer != null) {
            try {
                consumer.close();
            } catch (Throwable e) {
                log.warn("fail to close consumer -- " + e);
            }
        }

    }
}
