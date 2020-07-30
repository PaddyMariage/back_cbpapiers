package cbpapiers.app.cbpapiers.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailServiceImpl implements EmailService {

    private static final String ADDRESS = "adrien.fek@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(ADDRESS);
            message.setTo("justine.gracia@gmail.com");
            message.setSubject("test envoi mail sans fichier joint par Spring");
            message.setText("How are you ? Is that pdf generation going well ?");

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(ADDRESS);
            helper.setTo("justine.gracia@gmail.com");
            helper.setSubject("test envoi mail sans fichier joint par Spring");
            helper.setText("Hey ! Do you see my magnificent PDF ?");

            FileSystemResource file = new FileSystemResource(new File("à tester avec PDF generator"));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
