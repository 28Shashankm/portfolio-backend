INSERT INTO skills (name, category, proficiency_level, years_of_experience, display_order) VALUES
('JavaScript', 'Frontend', 95, NULL, 2),
('HTML/CSS', 'Frontend', 95, NULL, 1),
('Java', 'Backend', 90, NULL, 3),
('Spring Boot', 'Backend', 88, NULL, 4),
('Spring Data JPA', 'Backend', 85, NULL, 5),
('RESTful APIs', 'Backend', 90, NULL, 6),
('MySQL', 'Database', 80, NULL, 7),
('PostgreSQL', 'Database', 75, NULL, 8),
('Git', 'Tools', 90, NULL, 9);


INSERT INTO projects
(title, short_description, description, category, status, featured, display_order, created_at, updated_at)
VALUES
(
  'The Human-Animal Conflict Mitigation System',
  'Configured Java–Python interoperability to link backend workflows with AI-based predictions, ensuring accurate and timely mitigation alerts.',
  'Developed an application to mitigate human-animal conflicts using Java, Spring Boot, and MySQL, integrating Deep Learning Models in Python for intelligent animal detection. Built RESTful APIs to manage wildlife incidents, user registrations, and alert systems, supporting efficient field data collection and reporting.',
  'AI-Driven Web Application',
  'Completed',
  true,
  1,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
),
(
  'Hospital Management System',
  'Collaborative task management tool for teams',
  'Developed a Hospital Management System using Java and MySQL, implementing a well-structured database schema for patients, doctors, and appointments.',
  'Web',
  'Completed',
  true,
  2,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);


INSERT INTO project_technologies (project_id, technology) VALUES
(1, 'Java'),
(1, 'Spring Boot'),
(1, 'MySQL'),
(1, 'Spring Data REST API'),
(1, 'Deep Learning Model'),
(2, 'Java'),
(2, 'Spring Boot'),
(2, 'PostgreSQL'),
(2, 'HTML/CSS');
