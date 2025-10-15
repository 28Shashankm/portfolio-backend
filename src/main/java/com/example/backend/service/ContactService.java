package com.example.backend.service;

import com.example.backend.model.Contact;
import com.example.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired(required = false)
    private EmailService emailService;

    public Contact saveContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);

        // Send email notification (if email service is configured)
        if (emailService != null) {
            try {
                emailService.sendContactNotification(savedContact);
            } catch (Exception e) {
                // Log error but don't fail the request
                System.err.println("Failed to send email notification: " + e.getMessage());
            }
        }

        return savedContact;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public List<Contact> getUnreadContacts() {
        return contactRepository.findByIsReadFalseOrderByCreatedAtDesc();
    }

    public Optional<Contact> markAsRead(Long id) {
        return contactRepository.findById(id).map(contact -> {
            contact.setRead(true);
            return contactRepository.save(contact);
        });
    }

    public Optional<Contact> markAsReplied(Long id) {
        return contactRepository.findById(id).map(contact -> {
            contact.setReplied(true);
            contact.setRead(true);
            return contactRepository.save(contact);
        });
    }

    public boolean deleteContact(Long id) {
        return contactRepository.findById(id).map(contact -> {
            contactRepository.delete(contact);
            return true;
        }).orElse(false);
    }
}