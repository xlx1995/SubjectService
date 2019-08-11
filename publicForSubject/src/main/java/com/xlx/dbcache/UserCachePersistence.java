package com.xlx.dbcache;


import com.xlx.kafka.facotry.KafkaConsumerFactory;
import com.xlx.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/30 22:42
 * @Description:
 */
@Slf4j
public class UserCachePersistence implements RedisDistributedFacotry {

    /**
     * kafka生产者客户端
     */
    @Autowired
    private KafkaConsumerFactory kafkaConsumerFactory;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 用户信息缓存
     */
    private Map<String, Object> userChace;

    private volatile boolean isRunning = true;

    @Override
    public void init() {
        Runnable consumerTask = () -> {
            KafkaConsumer consumer = kafkaConsumerFactory.getConsumer("group1");
            consumer.subscribe(Arrays.asList(Constants.USERTOPIC));
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    for (ConsumerRecord<String, String> record : records) {
                        log.info("success to subscribe topic " + record.key() +record.value());
                    }
                }
            } catch (Throwable e){
                log.error("fail to connection consumer ex[{}]",new Object[]{e});
            }
        };
        taskExecutor.execute(consumerTask);




    }
}
