DROP SCHEMA IF EXISTS `commission`;
CREATE SCHEMA IF NOT EXISTS `commission` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `commission`;


DROP TABLE IF EXISTS `commission`.`roles`;
DROP TABLE IF EXISTS `commission`.`user_statuses`;
DROP TABLE IF EXISTS `commission`.`users`;
DROP TABLE IF EXISTS `commission`.`faculties`;


-- CREATE SCHEMA `commission` DEFAULT CHARACTER SET utf8 ;


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
    CONSTRAINT `fk_user_status`
        FOREIGN KEY (`status_id`)
            REFERENCES `commission`.`user_statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_role`
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
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `name_en`      VARCHAR(100) NOT NULL,
    `name_uk`      VARCHAR(100) NOT NULL,
    `total_seats`  INT          NOT NULL,
    `budget_seats` INT          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_en_UNIQUE` (`name_en` ASC),
    UNIQUE INDEX `name_uk_UNIQUE` (`name_uk` ASC)
);

INSERT INTO faculties
VALUES (DEFAULT, 'Faculty of Economics', 'Факультет Економіки', 25, 10);
INSERT INTO faculties
VALUES (DEFAULT, 'Faculty of Engineering', 'Факультет Інженерії', 20, 15);
INSERT INTO faculties
VALUES (DEFAULT, 'Faculty of Information Technologies', 'Факультет Інформацийних Технологій', 10, 5);



