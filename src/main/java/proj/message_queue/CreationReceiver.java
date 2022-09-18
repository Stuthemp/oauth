package proj.message_queue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import proj.service.FirstService;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * todo Document type CreationReciever
 */
@Component
public class CreationReceiver {

    private volatile boolean keepConsuming = true;
    FirstService firstService;

    @Autowired
    public CreationReceiver(FirstService firstService) {
        this.firstService = firstService;
    }

    @Value("${mq.server-1}")
    private  String server1;

    public void publishCreationInfo() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", server1);
        properties.put("group.id", "kinaction_helloconsumer");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(
                List.of("kinaction_helloworld")
            );

            while (keepConsuming) {
                ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(2500));
                for (ConsumerRecord<String, String> record :
                    records) {
                    System.out.println("kinaction_info offset = " + record.offset() + ", kinaction_value = " + record.value());
                }
            }
        }
    }

}
