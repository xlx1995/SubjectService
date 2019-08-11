package com.xlx.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/21 22:09
 * @Description:
 */
public interface  SendCallback extends Callback {
    @Override
    void onCompletion(RecordMetadata metadata, Exception exception);
}
