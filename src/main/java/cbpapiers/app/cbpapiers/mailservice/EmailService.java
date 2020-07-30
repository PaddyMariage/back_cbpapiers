package cbpapiers.app.cbpapiers.mailservice;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}

