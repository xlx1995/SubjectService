package com.xlx.client;

import org.apache.kafka.clients.producer.*;

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
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        //k ,v 的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.xlx.client.CustomerPartitions");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord("first", "nihao , java " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){
                        System.out.println(metadata.partition() + "----" + metadata.topic() +"------" + metadata.offset());
                    }else{
                        System.out.println("发送失败");
                    }
                }
            });
        }
        producer.close();


    }


}
