-- liquibase formatted sql
-- changeset dsazonov:1
    CREATE INDEX student_name_index ON student(name);
    CREATE INDEX faculty_name_index ON faculty(name);
    CREATE INDEX faculty_color_index ON faculty(color);

-- changeset dsazonov:2
    DROP INDEX faculty_name_index;
    DROP INDEX faculty_color_index;

-- changeset dsazonov:3
    CREATE INDEX faculty_color_and_name_index ON faculty(name, color);