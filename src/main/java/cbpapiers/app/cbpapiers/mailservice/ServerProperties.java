package cbpapiers.app.cbpapiers.mailservice;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class ServerProperties {

    @Bean
    public JavaMailSender getJavaMailSender() {

        return new JavaMailSenderImpl();

    }

}
