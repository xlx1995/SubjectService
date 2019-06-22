package com.xlx.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/17 23:00
 * @Description:
 */
public class KafkaSendMsgUtils {
    public static final  ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("/provider.xml");

    public static <K,T>void sendMessage(String topic, Integer partition, Long timestamp,  K key, T data) {
        KafkaTemplate<K, T> kafkaTemplate = (KafkaTemplate<K, T>) CONTEXT.getBean("kafkaTemplate");
        ListenableFuture<SendResult<K, T>> listenableFuture = null;
        if (kafkaTemplate.getDefaultTopic().equals(topic)) {
            listenableFuture =  kafkaTemplate.sendDefault(partition,timestamp,key,data);
        }else {
            listenableFuture = kafkaTemplate.send(topic, partition, timestamp, key, data);
        }
        //发送成功回调
        SuccessCallback<SendResult<K, T>> successCallback = new SuccessCallback<SendResult<K, T>>() {
            @Override
            public void onSuccess(SendResult<K, T> result) {
                //成功业务逻辑
                System.out.println("成功");
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //失败业务逻辑
                throw new RuntimeException(ex);
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) CONTEXT.getBean("kafkaTemplate");
            KafkaSendMsgUtils.sendMessage("topic1",0,null,"key","kafka-test");
        }
    }
}

