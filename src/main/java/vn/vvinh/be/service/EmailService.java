package vn.vvinh.be.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.security.TokenHandler;

@Service
public class EmailService {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TokenHandler tokenHandler;

    public void sendMailTemplate(Account account) {

        try {
            Context context = new Context();

            context.setVariable("name", account.getFullName());
            context.setVariable("email", account.getEmail());
            context.setVariable("link", "http://birthdaykids.fun:8081/verify?token=" + tokenHandler.generateToken(account));

            String text = templateEngine.process("emailtemplate", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(account.getEmail());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject("Verify Account");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }
}
