package com.example.alkemy.disney.service.implementation;

import com.example.alkemy.disney.service.EmailServiceInterface;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImplementation implements EmailServiceInterface {

    final static Logger LOGGER = Logger.getLogger(CharacterServiceImplementation.class);
    @Autowired
    private Environment env;

    @Value("${alkemy.disney.email.sender}")
    private String emailSender;

    @Value("${alkemy.disney.email.enabled}")
    private Boolean emailEnabled;


    @Override
    public void sendWelcomeEmailTo(String to) {
        if (!emailEnabled){
            return;
        }

        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", "Bienvenido/a a Alkemy disney");
        String subject = "Alkemy disney";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            LOGGER.info(response.getStatusCode());
            LOGGER.info(response.getBody());
            LOGGER.info(response.getHeaders());
        } catch (IOException e) {

            LOGGER.error("SEND EMAIL HAD A CONFLICT: "+e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
