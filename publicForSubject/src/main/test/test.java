import com.alibaba.fastjson.JSON;
import com.xlx.kafka.SendCallback;
import com.xlx.kafka.client.KafkaConsumerClient;
import com.xlx.kafka.client.KafkaProducerClient;
import com.xlx.db.entity.Person;

import com.xlx.kafka.facotry.KafkaConsumerFactory;
import com.xlx.zk.client.ZkClient;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.OpCode.exists;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 21:35
 * @Description:
 */
public class test {

    @Test
    public void t1(){

        Person person = new Person(11, "zs", "man");
        String s = JSON.toJSONString(person);
        System.out.println(s);
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person);
        persons.add(new Person(12,"lise","man"));
        String s1 = JSON.toJSONString(persons);
        System.out.println(s1);
        Person person1 = JSON.parseObject(s, Person.class);
        List<Person> people = JSON.parseArray(s1, Person.class);
        System.out.println(person1);
        System.out.println(people);


    }
    @Test
    public void producer(){
        KafkaProducerClient kafkaProducerClient = new KafkaProducerClient();
        Producer<String, String> kafkaProducer = kafkaProducerClient.getKafkaProducer();
        kafkaProducer.send(new ProducerRecord<>("user", "aaaa","aaaa"),new SendCallback(){
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null){
                    System.out.println("发送成功");
                }else{
                    kafkaProducerClient.close(kafkaProducer);
                }
            }
        });
        kafkaProducerClient.close(kafkaProducer);

    }
    @Test
    public void consumer(){
        KafkaConsumerFactory kafkaConsumerFactory = new KafkaConsumerFactory();
        KafkaConsumer consumer = kafkaConsumerFactory.getConsumer("group1");
        consumer.subscribe(Arrays.asList("first"));
        try {
            while (true){
                ConsumerRecords<String,String> records = consumer.poll(1000);
                for(ConsumerRecord<String,String> record : records){
                    System.out.printf("offset = %d ,key = %s ,value = %s%n" ,record.offset() ,record.key() ,record.value());
                }
            }
        }finally {
            KafkaConsumerClient.close(consumer);
        }

    }

    @Test
    public void zk() throws KeeperException, InterruptedException {

        ZkClient zkClient = new ZkClient();
        zkClient.createConnection();
        Stat exists = zkClient.exists("/brokers/topics/user", false);
        if(exists != null) {
            System.out.println("Node exists and the node version is " +
                    exists.getVersion());
        } else {
            System.out.println("Node does not exists");
        }
        zkClient.close();

    }
    @Test
    public void t(){



    }

}
