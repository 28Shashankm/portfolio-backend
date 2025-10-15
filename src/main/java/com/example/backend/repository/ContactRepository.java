// ContactRepository.java
package com.example.backend.repository;

import com.example.backend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByOrderByCreatedAtDesc();

    List<Contact> findByIsReadFalseOrderByCreatedAtDesc();

    List<Contact> findByIsRepliedFalseOrderByCreatedAtDesc();

    long countByIsReadFalse();
}
