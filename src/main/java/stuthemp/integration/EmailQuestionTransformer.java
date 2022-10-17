package stuthemp.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;

/**
 * todo Document type EmailQuestionTransformer
 */
@Component
public class EmailQuestionTransformer extends AbstractMailMessageTransformer<EmailQuestion> {

    private static Logger log =
        LoggerFactory.getLogger(EmailQuestionTransformer.class);

    private static final String SUBJECT_KEYWORDS = "EMAIL QUESTION";

    @Override
    protected AbstractIntegrationMessageBuilder<EmailQuestion> doTransform(javax.mail.Message mailMessage) throws Exception {
        EmailQuestion emailQuestion = processPayload(mailMessage);
        return MessageBuilder.withPayload(emailQuestion);
    }

    private EmailQuestion processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if(subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                String time = mailMessage.getReceivedDate().toString();
                return parseEmailToQuestion(email, time, content);
            }
        } catch (MessagingException e) {
            log.error("Messaging Exception: {}", e);
        } catch (IOException e) {
            log.error("IOException: {}", e);
        }
        return null;
    }

    private EmailQuestion parseEmailToQuestion(String email, String dateTime, String content) {
        EmailQuestion question = new EmailQuestion(email, dateTime);

        content = content.replaceAll("<div>","");
        content = content.replaceAll("</div>","");
        String[] lines = content.split(":");

        if(lines.length != 7)
            throw new IllegalArgumentException("Wrong content of message");

        question.setQuestionLocation(lines[0]);
        question.setRightAnswer(lines[1]);
        question.setWrongAnswer1(lines[2]);
        question.setWrongAnswer2(lines[3]);
        question.setWrongAnswer3(lines[4]);
        question.setDocRef(lines[5]);
        question.setQuestionType(lines[6]);
        return question;
    }

}
