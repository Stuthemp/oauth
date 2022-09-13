package stuthemp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Main config
 */
@Configuration
public class ProjectConfig{

    @Value("${oauth.client.id}")
    private String clientId;

    @Value("${oauth.client.secret}")
    private String clientSecret;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Login();

        http.csrf().disable();

        http.authorizeRequests()
            .mvcMatchers(HttpMethod.GET,"/hello")
            .authenticated()
            .anyRequest()
            .permitAll();

        return http.build();
    }

    private ClientRegistration clientRegistration() {
        System.out.println(clientId);
        return CommonOAuth2Provider.GITHUB
            .getBuilder("github")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        var c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }
}
