package stuthemp.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * todo Document type AlgofanPublishEmailIntegrationConfig
 */
@Configuration
public class AlgofanPublishEmailIntegrationConfig {

    @Bean
    public IntegrationFlow algofanEmailFlow(
        EmailQuestionTransformer transformer,
        QuestionSubmitMessageHandler handler
    ) {
        return IntegrationFlows
            .from(Mail.imapInboundAdapter(
                        "imaps://"
                            + URLEncoder.encode("qwegras@yandex.ru", Charset.defaultCharset())
                            + ":"
                            + URLEncoder.encode("mfoacgcmxzhzwvcc", Charset.defaultCharset())
                            + "@imap.yandex.ru:993/inbox"
                    ).shouldDeleteMessages(true)
                    .javaMailProperties(p -> {
                        p.put("mail.debug", "true");
                        p.put("mail.imaps.ssl.trust", "*");
                        p.put("mail.imaps.auth", "true");
                    }),
        e -> e.poller(
            Pollers.fixedDelay(10000L)
        ))
            .transform(transformer)
            .handle(handler)
            .get();
    }


}
