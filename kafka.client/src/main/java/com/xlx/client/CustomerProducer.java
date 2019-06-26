package com.xlx.client;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.util.Properties;

/**
  *@Auther: 徐林啸
  *@Date: 2019/6/2 22:24
  *@Description:
 */
public class CustomerProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.159.128:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        props.put("retries",3);
        props.put("batch.size",323840);
        props.put("linger.ms", 10);
        props.put("buffer.memory", 33554432);
        props.put("max.block.ms", 3000);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        //k ,v 的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord("first", "nihao , java " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){
                        System.out.println(metadata.partition() + "----" + metadata.topic() +"------" + metadata.offset());
                    }else{
                        if(exception instanceof RetriableException){
                            //处理可重试异常
                        }else{
                            //处理不可重试异常
                        }

                    }
                }
            });
        }
        producer.close();


    }


}
