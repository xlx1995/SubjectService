import com.alibaba.fastjson.JSON;
import com.xlx.kafka.client.KafkaConsumerClient;
import com.xlx.kafka.client.KafkaProducerClient;
import com.xlx.db.entity.Person;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        kafkaProducerClient.close(kafkaProducer);

    }
    @Test
    public void consumer(){
        KafkaConsumerClient kafkaConsumerClient = new KafkaConsumerClient();
        KafkaConsumer consumer = kafkaConsumerClient.getKafkaConsumer("test-consumer-group");
        kafkaConsumerClient.close(consumer);
    }

}
