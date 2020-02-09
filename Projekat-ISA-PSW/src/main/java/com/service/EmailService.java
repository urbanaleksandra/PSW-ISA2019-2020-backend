package com.service;


import com.model.*;
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
    public void sendNotificaitionAsync(Patient user, ConfirmationTokenRegistration confirmationToken) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject("Complete Registration!");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("To confirm your account, please click here : "
                +"http://localhost:4200/confirm-account?token="+confirmationToken.getConfirmationToken());
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendNotificaitionAsync2(String email, String message) throws MailException, InterruptedException {
        System.out.println("Slanje emaila2...");

        String email2 = email + ".com";
        System.out.println(email);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email2);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Denied request");

        mail.setText("Mr/Mrs, "  + ",\n\nyour request is denied.\n\n" + message);
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendNotificaitionAsync3() throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("zeki.sipovac@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New request");
        mail.setText("Mr/Mrs, you have new request for reservation of hospital room. Please check your list with requests. ");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }



    @Async
    public void sendNotificaitionAsyncc3(ClinicAdministrator cadmin) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(cadmin.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New request");
        mail.setText("Mr/Mrs, you have new request for reservation of hospital room. Please check your list with requests. ");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }
    @Async
    public void sendNotificaitionAsync4(Patient patient) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Accepted");
        mail.setText("Your appointment is accepted. ");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async

    public void sendDoctorNotificaition(Surgery surgery, Doctor doctor) throws MailException, InterruptedException {
        System.out.println("Slanje emaila doktoru...");

        System.out.println(doctor.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(doctor.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Surgery info");

        mail.setText("Mr/Mrs " + doctor.getFirstName() + " " + doctor.getLastName() +
                ","  + "\n\nYou have new scheduled surgery\n\nDate: " + surgery.getDate() +
                "\nHospital room: "+ surgery.getHospitalRoom().getName()+ " no." + surgery.getHospitalRoom().getRoom_number()
                + ".\n\nAll the best,\nYour clinic.");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendPatientNotificaition(Surgery surgery, Patient patient) throws MailException, InterruptedException {
        System.out.println("Slanje emaila pacijentu...");

        System.out.println(patient.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Surgery info");

        mail.setText("Mr/Mrs " + patient.getFirstName() + " " + patient.getLastName() +
                "," + "\n\nYou have new scheduled surgery\n\nDate: " + surgery.getDate() +
                "\nHospital room: " + surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
                + ".\n\nAll the best,\nYour clinic.");
    }

    @Async
    public void sendPatientNotificaition2(Appointment surgery, Patient patient) throws MailException, InterruptedException {
        System.out.println("Slanje emaila pacijentu...");

        System.out.println(patient.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Surgery info");

        mail.setText("Mr/Mrs " + patient.getFirstName() + " " + patient.getLastName() +
                "," + "\n\nYou have new scheduled surgery\n\nDate: " + surgery.getDate() +
                "\nHospital room: " + surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
                + ".\n\nAll the best,\nYour clinic.");
    }



    @Async
    public void sendNotificaitionAsync5() throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("urb.saska@gmail.com"); //"zekica997@gmail.com"
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New request");
        mail.setText("Mr/Mrs, your request for holiday has been approved. Enjoy your holiday. ");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendNotificaitionAsync6(String message) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("urb.saska@gmail.com"); //"zekica997@gmail.com"
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New request");
        mail.setText("Mr/Mrs, your request for holiday has been rejected. Here is a short explanation why: "+ message);
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendPatientNotificaition7(Appointment surgery, Patient patient) throws MailException, InterruptedException {
        System.out.println("Slanje emaila pacijentu...");

        System.out.println(patient.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Surgery info");

        mail.setText("Mr/Mrs " + patient.getFirstName() + " " + patient.getLastName() +
                "," + "\n\nYour appointment for \n\nDate: " + surgery.getDate() +
                "\nHospital room: " + surgery.getHospitalRoom().getName() + " no." + surgery.getHospitalRoom().getRoom_number()
                + "has been accepted .\n\nAll the best,\nYour clinic.");
    }
    @Async
    public void sendPatientNotificaition7(Nurse nurse) throws MailException, InterruptedException {
        System.out.println("Slanje emaila pacijentu...");

        System.out.println(nurse.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(nurse.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Nurse info");

        mail.setText("Mr/Mrs " + nurse.getFirstName() + ".");
    }

    @Async
    public void sendNotificaitionAsync8(ClinicAdministrator ca) throws MailException, InterruptedException {
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(ca.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("New request");
        mail.setText("Mr/Mrs, you have a new request to make a new appointment. Please check your list with requests. ");
        try{
            javaMailSender.send(mail);
        }
        catch( Exception e ){
            System.out.println("nije javaMailSender.send(mail); prosao");
        }
    }

    @Async
    public void sendPatientNotificaition9(Nurse nurse) throws MailException, InterruptedException {
        System.out.println("Slanje emaila pacijentu...");

        System.out.println(nurse.getEmail());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(nurse.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Nurse info");

        mail.setText("Mr/Mrs " + nurse.getFirstName() + ".");
    }

}