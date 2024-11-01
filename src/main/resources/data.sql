-- Insert addresses
INSERT INTO address (street, city) VALUES ('123 Maple Street', 'Springfield');
INSERT INTO address (street, city) VALUES ('456 Oak Avenue', 'Shelbyville');
INSERT INTO address (street, city) VALUES ('789 Pine Road', 'Capitol City');

-- Insert students
INSERT INTO student (first_name, last_name, email, address_id) VALUES ('Victor', 'Silva', 'victor@example.com', 1);
INSERT INTO student (first_name, last_name, email, address_id) VALUES ('John', 'Doe', 'john@example.com', 2);
INSERT INTO student (first_name, last_name, email, address_id) VALUES ('Jane', 'Smith', 'jane@example.com', 3);

-- Insert subjects for the first student
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('Java', 95.0, 1);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MySQL', 88.5, 1);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MongoDB', 90.0, 1);

-- Insert subjects for the second student
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('Java', 75.0, 2);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MySQL', 80.0, 2);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MongoDB', 85.0, 2);

-- Insert subjects for the third student
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('Java', 92.0, 3);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MySQL', 78.0, 3);
INSERT INTO subject (subject_name, marks_obtained, student_id) VALUES ('MongoDB', 88.0, 3);
