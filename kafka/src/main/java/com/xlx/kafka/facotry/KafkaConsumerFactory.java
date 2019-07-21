package com.xlx.kafka.facotry;

import com.xlx.common.KafkaConstans;
import com.xlx.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/21 13:18
 * @Description:
 */
@Slf4j
public class KafkaConsumerFactory {


    public KafkaConsumer getConsumer(String groupId){
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
        finally {
            IOUtils.close(inputStream);
        }
        return consumer;

    }


}
