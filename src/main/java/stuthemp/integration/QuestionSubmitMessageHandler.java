package stuthemp.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import stuthemp.serive.QuestionService;

/**
 * todo Document type QuesionSubmitMessagehandler
 */
@Component
public class QuestionSubmitMessageHandler implements GenericHandler<EmailQuestion> {

    QuestionService questionService;

    @Autowired
    public QuestionSubmitMessageHandler(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Object handle(EmailQuestion payload, MessageHeaders headers) {
        System.out.println(payload);
        return null;
    }
}
