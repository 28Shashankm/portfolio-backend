package com.example.backend.repository;

import com.example.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByFeaturedTrue();

    List<Project> findByCategoryOrderByDisplayOrderAsc(String category);

    List<Project> findByStatusOrderByDisplayOrderAsc(String status);

    List<Project> findAllByOrderByDisplayOrderAsc();

    @Query("SELECT p FROM Project p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Project> searchProjects(@Param("keyword") String keyword);

    @Query("SELECT p FROM Project p JOIN p.technologies t WHERE LOWER(t) = LOWER(:technology)")
    List<Project> findByTechnology(@Param("technology") String technology);
}