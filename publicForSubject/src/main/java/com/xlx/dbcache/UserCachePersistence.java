package com.xlx.dbcache;

import com.xlx.kafka.client.KafkaConsumerClient;
import com.xlx.kafka.client.KafkaProducerClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/30 22:42
 * @Description:
 */
public class UserCachePersistence implements RedisDistributedFacotry {

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private KafkaConsumerClient kafkaConsumerClient;

    @Override
    public void init() {

    }
}
