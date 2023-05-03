
SELECT s.name, s.age, f.name
FROM student as s
         LEFT JOIN faculty f on s.faculty_id = f.id;

SELECT s.name, s.age
FROM student as s
         RIGHT JOIN avatar a on s.id = a.student_id;