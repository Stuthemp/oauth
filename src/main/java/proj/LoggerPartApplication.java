package proj;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import proj.message_queue.CreationReceiver;

/**
 * todo Document type LoggerPartApplication
 */
@SpringBootApplication
public class LoggerPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerPartApplication.class, args);
    }

}
