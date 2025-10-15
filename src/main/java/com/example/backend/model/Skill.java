package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    @Column(nullable = false)
    private String name;

    @Column(name = "category")
    private String category; // Frontend, Backend, Database, Tools, etc.

    @Min(0)
    @Max(100)
    @Column(name = "proficiency_level")
    private Integer proficiencyLevel; // 0-100

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
}