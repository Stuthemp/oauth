package stuthemp.message_queue;

import org.springframework.beans.factory.annotation.Value;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import stuthemp.model.Question;
import stuthemp.utils.JsonConverter;

import java.util.Properties;

/**
 * Publish info about new question to queue
 */
@Component
public class CreationPublisher {

    @Value("${mq.server-1}")
    private  String server1;
//    @Value("${mq.server-2}")
//    private String server2;
//    @Value("${mq.server-3}")
//    private String server3;

    @Value("${mq.message-queue-name}")
    private String messageQueueName;

    public void publishCreationInfo(Question question, String creatorName) {
        Properties properties = new Properties();
        //String servers = server1 + "," + server2 + "," + server3;

        properties.put("bootstrap.servers", server1);
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        String message = JsonConverter.toJSON(question, creatorName);

        try(Producer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>
                (
                    messageQueueName, null, message
                );
            producer.send(record);
        }
    }

}
