package com.hiringportal.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    @Value("${EMAIL_HOST}")
    private String host;
    private String FROM_EMAIL = "noreply@batmandiri.com";
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void sendEmailVerification(String name, String to, String token) {
        try {
            Context context = new Context();
            context.setVariables(Map.of(
                    "message", generateMessageEmailVerification(name),
                    "url", generateVerifyEmailUrl(token)
            ));
            String text = templateEngine.process("emailtemplate", context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("New Account Verification");
            helper.setFrom(FROM_EMAIL);
            helper.setTo(to);
            helper.setText(text, true);
            javaMailSender.send(message);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendEmailTest(String name, String to, String token) {

    }

    private String generateMessageEmailVerification(String name){
        return "Hello " + name + ", your account has been created";
    }

    // private String generateVerifyEmailUrl(String token){
    //     return host + "/api/auth/verify-email?token=" + token;
    // }

    private String generateVerifyEmailUrl(String token){
        return "http://localhost:3000/email-verification?token=" + token;
    }
}
