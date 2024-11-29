package com.samyati.user_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.samyati.user_management.exceptions.ApplicationException;
import com.samyati.user_management.exceptions.ErrorCode;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtp(String email, String Otp){
        try {
            // Create a MimeMessage
            MimeMessage message = javaMailSender.createMimeMessage();

            // Use MimeMessageHelper for advanced options
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText(
                "<html><body>" +
                "<h3>Dear User,</h3>" +
                "<p>Your OTP code is: <b>" + Otp + "</b></p>" +
                "<p>Thank you for using our service.</p>" +
                "</body></html>", 
                true
            );

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new ApplicationException(ErrorCode.GENERIC_ERROR);
        }
    }
    
}
