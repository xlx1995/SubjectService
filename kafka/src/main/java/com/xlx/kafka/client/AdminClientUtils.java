package com.xlx.kafka.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.*;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/16 23:15
 * @Description:
 */
@Slf4j
public class AdminClientUtils{

    /**
     * 创建topic
     * @param adminClient
     * @param topics
     */
    public static void createTopics(AdminClient adminClient,String topics){

        NewTopic newTopic = new NewTopic(topics, 1, (short)1);
        CreateTopicsResult ret = adminClient.createTopics(Arrays.asList(newTopic));
        try {
            ret.all().get();
        } catch (InterruptedException e) {
            log.info("fail to create topic[{}],ex[{}]",new Object[]{topics,e});
        } catch (ExecutionException e) {
            log.info("fail to create topic[{}],ex[{}]",new Object[]{topics,e});
        }
    }

    /**
     * 获取kafka集群信息
     */
    public static void describeCluster(AdminClient adminClient){
        try {
            DescribeClusterResult ret = adminClient.describeCluster();
            System.out.println(String.format("Cluster id : %s,controller: %s", ret.clusterId().get(),ret.controller().get()));
            System.out.println("Current cluster nodes info :");
            for(Node node : ret.nodes().get()){
                System.out.println(node);
            }
        } catch (InterruptedException e) {
            log.info("fail to get kafakinfo,ex[{}]",new Object[]{e});
        } catch (ExecutionException e) {
            log.info("fail to get kafkainfo,ex[{}]",new Object[]{e});
        }
    }

    /**
     * 删除指定topic
     * @param adminClient
     * @param topic
     */
    public static void deleteTopics(AdminClient adminClient , String topic){
        try {
            KafkaFuture<Void> future = adminClient.deleteTopics(Arrays.asList(topic)).all();
            future.get();
        } catch (InterruptedException e) {
            log.info("fail to delete topic[{}],ex[{}]",new Object[]{topic,e});
        } catch (ExecutionException e) {
            log.info("fail to delete topic[{}],ex[{}]",new Object[]{topic,e});
        }
    }

    /**
     * 获取topic详细信息
     * @param adminClient
     * @param topic
     * @return
     */
    public static Map<String,TopicDescription> desrcibeTopics(AdminClient adminClient,String topic){
        DescribeTopicsResult ret = adminClient.describeTopics(Arrays.asList(topic,"_consumer_offsets"));
        Map<String,TopicDescription> topics = null;
        try {
            topics = ret.all().get();
        } catch (InterruptedException e) {
            log.info("fail to get topic[{}],ex[{}]",new Object[]{topic,e});
        } catch (ExecutionException e) {
            log.info("fail to get topic[{}],ex[{}]",new Object[]{topic,e});
        }
        return topics;
    }

    public static void close(AdminClient adminClient){
        try {
            if(null != adminClient){
                adminClient.close();
            }
        }catch (Throwable e){
            log.error("fail to close AdminClient ex{}",e);
        }
    }
}
