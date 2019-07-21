package com.xlx.kafka.client;


import com.xlx.common.KafkaConstans;

import com.xlx.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/28 20:20
 * @Description:
 */
@Slf4j
public class KafkaConsumerClient extends KafkaConsumer{



    public KafkaConsumerClient(Map configs) {
        super(configs);
    }

    public KafkaConsumerClient(Map configs, Deserializer keyDeserializer, Deserializer valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);
    }

    public KafkaConsumerClient(Properties properties) {
        super(properties);
    }

    public KafkaConsumerClient(Properties properties, Deserializer keyDeserializer, Deserializer valueDeserializer) {
        super(properties, keyDeserializer, valueDeserializer);
    }




    public static void close(Consumer consumer) {

        if (consumer != null) {
            try {
                consumer.close();
            } catch (Throwable e) {
                log.warn("fail to close consumer -- " + e);
            }
        }

    }
}
