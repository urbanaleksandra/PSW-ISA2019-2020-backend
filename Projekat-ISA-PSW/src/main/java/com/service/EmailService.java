package com.service;


import com.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
     */
    @Autowired
    private Environment env;

    /*
     * Anotacija za oznacavanje asinhronog zadatka
     * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
     */
    @Async
    public void sendNotificaitionAsync(Patient user) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Accepted request");
        mail.setText("Mr/Mrs " + user.getFirstName() + ",\n\nyour request is accepted.");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendNotificaitionAsync2(Patient user, String message) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Denied request");

        mail.setText("Mr/Mrs " + user.getFirstName() + ",\n\nyour request is denied.\n\n" + message);
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }



}