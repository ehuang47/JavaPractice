show tables;
select * from quiz;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO user (name, username, email)
VALUES ('name1', 'username1', 'email1'),
       ('name2', 'username2', 'email2'),
       ('name3', 'username3', 'email3');


select * from user;


DROP TABLE IF EXISTS quiz;
CREATE TABLE quiz
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

INSERT INTO quiz (name, user_id)
VALUES ('quiz1', 1),
       ('quiz2', 2),
       ('quiz3', 3);

select * from quiz;