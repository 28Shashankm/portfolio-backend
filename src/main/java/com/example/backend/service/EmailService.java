package com.example.backend.service;

import com.example.backend.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    @Value("${portfolio.admin.email:admin@example.com}")
    private String adminEmail;

    public void sendContactNotification(Contact contact) {
        if (mailSender == null) {
            System.out.println("Mail sender not configured. Email notification skipped.");
            return;
        }

        try {
            // Send notification to admin
            SimpleMailMessage adminMessage = new SimpleMailMessage();
            adminMessage.setFrom(fromEmail);
            adminMessage.setTo(adminEmail);
            adminMessage.setSubject("New Contact Form Submission: " + contact.getSubject());
            adminMessage.setText(buildAdminEmailBody(contact));
            mailSender.send(adminMessage);

            // Send confirmation to user
            SimpleMailMessage userMessage = new SimpleMailMessage();
            userMessage.setFrom(fromEmail);
            userMessage.setTo(contact.getEmail());
            userMessage.setSubject("Thank you for contacting me!");
            userMessage.setText(buildUserEmailBody(contact));
            mailSender.send(userMessage);

            System.out.println("Email notifications sent successfully");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        }
    }

    private String buildAdminEmailBody(Contact contact) {
        return String.format("""
            New contact form submission received:

            Name: %s
            Email: %s
            Phone: %s
            Subject: %s

            Message:
            %s

            ---
            Received at: %s
            """,
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "Not provided",
                contact.getSubject(),
                contact.getMessage(),
                contact.getCreatedAt()
        );
    }

    private String buildUserEmailBody(Contact contact) {
        return String.format("""
            Hello %s,

            Thank you for reaching out! I have received your message and will get back to you as soon as possible.

            Here's a copy of your message:

            Subject: %s
            Message: %s

            Best regards,
            Your Name
            """,
                contact.getName(),
                contact.getSubject(),
                contact.getMessage()
        );
    }
}