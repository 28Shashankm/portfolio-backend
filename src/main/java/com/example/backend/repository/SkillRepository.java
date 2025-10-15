// SkillRepository.java
package com.example.backend.repository;

import com.example.backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAllByOrderByDisplayOrderAsc();

    List<Skill> findByCategoryOrderByDisplayOrderAsc(String category);

    List<Skill> findByProficiencyLevelGreaterThanEqual(Integer minProficiency);
}