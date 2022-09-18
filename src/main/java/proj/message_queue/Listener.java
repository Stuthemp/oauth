package proj.message_queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import proj.service.FirstService;

/**
 * todo Document type Listener
 */
public class Listener {

    @Autowired
    FirstService service;

    @KafkaListener(id = "listen1", topics = "kinaction_helloworld")
    public void listen1(String in) {
        service.addQuestion(in);
        System.out.println(in);
    }
}
