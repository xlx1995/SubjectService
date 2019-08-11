package com.xlx.kafka;

import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Configurable;

import java.io.Closeable;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/21 22:37
 * @Description:
 */
public interface Partitioner extends Configurable, Closeable {

    /**
     *  计算给定消息要发到哪个分区
     * @param topic 主题
     * @param key   消息键值
     * @param keyBytes 键值序列化数组
     * @param value 消息体
     * @param valueBytes 消息体序列化数组
     * @param cluster 集群元数据
     * @return
     */
    int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster);

    /**
     * 关闭partitioner
     */
    public void close();

}
