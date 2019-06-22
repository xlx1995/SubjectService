package KafkaProducerTest;

import com.xlx.util.KafkaProducerServer;

import java.util.Map;



public class KafkaProducerTest {
    public static void main(String[] args) {

        KafkaProducerServer kafkaProducer = new KafkaProducerServer();
        String topic = "first";
        String value = "test";
        String ifPartition = "0";
        Integer partitionNum = 3;
        String role = "test";//用来生成key
        Map<String,Object> res = kafkaProducer.sndMesForTemplate
                (topic, value, ifPartition, partitionNum, role);

        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);
    }
}