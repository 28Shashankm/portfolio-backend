// ExperienceRepository.java
package com.example.backend.repository;

import com.example.backend.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findAllByOrderByStartDateDesc();

    List<Experience> findByIsCurrentTrue();

    List<Experience> findByCompanyOrderByStartDateDesc(String company);

    List<Experience> findAllByOrderByDisplayOrderAsc();
}