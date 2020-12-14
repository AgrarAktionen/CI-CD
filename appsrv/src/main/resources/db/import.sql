UPDATE hibernate_sequence set next_val = 1000;

INSERT INTO School(id, name, street) values
    (1, 'HTL-Perg', 'Machlandstr. 48'),
    (2, 'HTL-Leonding', 'Limesstrasse 8-12');
INSERT INTO Course(id, name) values(1, '4BHIF');
INSERT INTO School_Course(School_id, courses_id) VALUES(1, 1);

INSERT INTO Person(id, LastName, FirstName, MatNr) values
    (1, 'Doe', 'John', '1234'),
    (2, 'Roe', 'Jane', '5678'),
    (3, 'Sixpack', 'Joe', '9012');

INSERT INTO Course_Person(Course_id, persons_id) VALUES(1, 1);
INSERT INTO Course_Person(Course_id, persons_id) VALUES(1, 2);
INSERT INTO Course_Person(Course_id, persons_id) VALUES(1, 3);

