DROP SCHEMA IF EXISTS `commission`;
CREATE SCHEMA IF NOT EXISTS `commission` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `commission`;


DROP TABLE IF EXISTS `commission`.`roles`;
DROP TABLE IF EXISTS `commission`.`user_statuses`;
DROP TABLE IF EXISTS `commission`.`users`;
DROP TABLE IF EXISTS `commission`.`faculties`;



CREATE TABLE `commission`.`roles`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

INSERT INTO roles
VALUES (0, 'user');
INSERT INTO roles
VALUES (1, 'admin');



CREATE TABLE `commission`.`user_statuses`
(
    `id`     INT         NOT NULL,
    `status` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE
);

INSERT INTO user_statuses
VALUES (0, 'unblocked');
INSERT INTO user_statuses
VALUES (1, 'blocked');



CREATE TABLE `commission`.`users`
(
    `id`               INT          NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(45)  NOT NULL,
    `surname`          VARCHAR(45)  NOT NULL,
    `patronymic`       VARCHAR(45)  NOT NULL,
    `email`            VARCHAR(255) NOT NULL,
    `password`         VARCHAR(255) NOT NULL,
    `region`           VARCHAR(45)  NOT NULL,
    `city`             VARCHAR(45)  NOT NULL,
    `institution_name` VARCHAR(45)  NOT NULL,
    `role_id`          INT          NOT NULL DEFAULT 0,
    `status_id`        INT          NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `user_status_idx` (`status_id` ASC) VISIBLE,
    INDEX `user_role_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_user_statuses_id`
        FOREIGN KEY (`status_id`)
            REFERENCES `commission`.`user_statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_roles_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `commission`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO users
VALUES (DEFAULT, 'Admin', 'Admin', 'Admin', 'admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', 'Admin obl.',
        'Admin', 'Admin №1', 1, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Denys', 'Denysov', 'Denysovich', 'user@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Kyiv obl.',
        'Kiyv', 'School #1', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Vitaly', 'Vitaliev', 'Vitalievych', 'user1@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Kharkiv obl.', 'Kharkiv', 'School #2', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Sergiy', 'Sergiiev', 'Sergiyovych', 'user2@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Luhansk obl.', 'Luhansk', 'School #3', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Volodymyr', 'Volodymyrov', 'Volodymyrovych', 'user3@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Donetsk obl.', 'Donetsk', 'School #4', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Artyom', 'Artyomov', 'Artyomovych', 'user4@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Cherkasy obl.', 'Cherkasy', 'School #5', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Danylo', 'Danylov', 'Danylovych', 'user5@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Poltava obl.', 'Poltava', 'School #6', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Oleksandr', 'Oleksandrov', 'Oleksandrovych', 'user6@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Odesa obl.', 'Odesa', 'School #7', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Yevgen', 'Yevgenov', 'Yevgenovych', 'user7@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Sumy obl.',
        'Sumy', 'School #8', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Alla', 'Allova', 'Allivna', 'user8@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Zaporizhia obl.',
        'Zaporizhia', 'School #9', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Alina', 'Alinova', 'Alinivna', 'user9@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Lviv obl.',
        'Lviv', 'School #10', DEFAULT, DEFAULT);



CREATE TABLE `commission`.`faculties`
(
    `id`           INT          NOT NULL,
    `name_en`      VARCHAR(100) NOT NULL,
    `name_uk`      VARCHAR(100) NOT NULL,
    `total_seats`  INT          NOT NULL,
    `budget_seats` INT          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_en_UNIQUE` (`name_en` ASC),
    UNIQUE INDEX `name_uk_UNIQUE` (`name_uk` ASC)
);

INSERT INTO faculties
VALUES (1, 'Faculty of Economics', 'Факультет Економіки', 25, 10);
INSERT INTO faculties
VALUES (2, 'Faculty of Engineering', 'Факультет Інженерії', 20, 15);
INSERT INTO faculties
VALUES (3, 'Faculty of Information Technologies', 'Факультет Інформацийних Технологій', 10, 5);



CREATE TABLE `commission`.`criteria`
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `name_en` VARCHAR(45) NOT NULL,
    `name_uk` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_en_UNIQUE` (`name_en` ASC) VISIBLE,
    UNIQUE INDEX `name_uk_UNIQUE` (`name_uk` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

INSERT INTO criteria
VALUES (DEFAULT, 'Average certificate grade', 'Середній бал атестату');
INSERT INTO criteria
VALUES (DEFAULT, 'Ukrainian language', 'Українська мова');
INSERT INTO criteria
VALUES (DEFAULT, 'Foreign language', 'Іноземна мова');
INSERT INTO criteria
VALUES (DEFAULT, 'Biology', 'Біологія');
INSERT INTO criteria
VALUES (DEFAULT, 'Chemistry', 'Хімія');
INSERT INTO criteria
VALUES (DEFAULT, 'Math', 'Математика');
INSERT INTO criteria
VALUES (DEFAULT, 'Physics', 'Фізика');
INSERT INTO criteria
VALUES (DEFAULT, 'History of Ukraine', 'Історія України');
INSERT INTO criteria
VALUES (DEFAULT, 'Geography', 'Географія');



CREATE TABLE `commission`.`faculty_criteria`
(
    `faculty_id`   INT NOT NULL,
    `criterion_id` INT NOT NULL,
    PRIMARY KEY (`faculty_id`, `criterion_id`),
    INDEX `fk_criterion_id_idx` (`criterion_id` ASC) VISIBLE,
    INDEX `fk_faculty_id_idx` (`faculty_id` ASC) VISIBLE,
    CONSTRAINT `fk_faculty_criteria_faculties_id`
        FOREIGN KEY (`faculty_id`)
            REFERENCES `commission`.`faculties` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_faculty_criteria_criteria_id`
        FOREIGN KEY (`criterion_id`)
            REFERENCES `commission`.`criteria` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

INSERT INTO faculty_criteria
VALUES (1, 1);
INSERT INTO faculty_criteria
VALUES (1, 2);
INSERT INTO faculty_criteria
VALUES (1, 6);
INSERT INTO faculty_criteria
VALUES (1, 9);
INSERT INTO faculty_criteria
VALUES (2, 1);
INSERT INTO faculty_criteria
VALUES (2, 2);
INSERT INTO faculty_criteria
VALUES (2, 6);
INSERT INTO faculty_criteria
VALUES (2, 7);
INSERT INTO faculty_criteria
VALUES (3, 1);
INSERT INTO faculty_criteria
VALUES (3, 2);
INSERT INTO faculty_criteria
VALUES (3, 3);
INSERT INTO faculty_criteria
VALUES (3, 6);



CREATE TABLE `commission`.`user_marks`
(
    `user_id`      INT    NOT NULL,
    `criterion_id` INT    NOT NULL,
    `mark`         INT(3) NOT NULL,
    PRIMARY KEY (`user_id`, `criterion_id`),
    INDEX `fk_user_marks_criteria_id_idx` (`criterion_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_marks_users_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `commission`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_marks_criteria_id`
        FOREIGN KEY (`criterion_id`)
            REFERENCES `commission`.`criteria` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;



CREATE TABLE `commission`.`applications`
(
    `user_id`    INT NOT NULL,
    `faculty_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `faculty_id`),
    INDEX `fk_applications_faculties_id_idx` (`faculty_id` ASC) VISIBLE,
    CONSTRAINT `fk_applications_users_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `commission`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_applications_faculties_id`
        FOREIGN KEY (`faculty_id`)
            REFERENCES `commission`.`faculties` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
