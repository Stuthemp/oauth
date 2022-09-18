package proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import proj.message_queue.Listener;

/**
 * todo Document type Config
 */
@Configuration
@EnableKafka
public class Config {

    @Bean
    public Listener listener() {
        return new Listener();
    }
}
