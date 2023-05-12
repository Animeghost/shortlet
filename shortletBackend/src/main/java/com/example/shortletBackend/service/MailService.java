package com.example.shortletBackend.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service @AllArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private final SpringTemplateEngine templateEngine;


    public void sendSimpleMessage(String recieverEmail, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(System.getenv("email"));
        message.setTo(recieverEmail);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
        System.out.println("email was sent ");
    }

    public void sendHtmlMessage(String recieverEmail, String subject,String text, String userName,String template) throws MessagingException {
        MimeMessage message= emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("name",userName);
        context.setVariable("text",text);

        // adding images to the email templates
        context.setVariable("imageResourceName","https://mzvfuo.stripocdn.email/content/guids/CABINET_4f36af74c8b5fe001dcbba8d0e9984e451095ae855f5a3e83c3ec23875fce45d/images/g2e0a6c8042bf57318600c984b0340f8fbf61b0f09b486fcfae9dbb3c35645da5083cf17645e31a6f62de5c0d3767c32b_640.jpeg");
        context.setVariable("logo","https://mzvfuo.stripocdn.email/content/guids/CABINET_ff06f0f6afff91e4506ef568b46158c8/images/44591551869356388.png");
        context.setVariable("Fblogo","https://mzvfuo.stripocdn.email/content/assets/img/social-icons/circle-black/facebook-circle-black.png");
        context.setVariable("Twitterlogo","https://mzvfuo.stripocdn.email/content/assets/img/social-icons/circle-black/facebook-circle-black.png");
        context.setVariable("Instalogo","https://mzvfuo.stripocdn.email/content/assets/img/social-icons/circle-black/instagram-circle-black.png");
        context.setVariable("YTlogo","https://mzvfuo.stripocdn.email/content/assets/img/social-icons/circle-black/youtube-circle-black.png");
        helper.setFrom(System.getenv("email"));
        helper.setTo(recieverEmail);
        helper.setSubject(subject);
        String html = templateEngine.process(template,context);

        helper.setText(html,true);


        emailSender.send(message);
    }
}
