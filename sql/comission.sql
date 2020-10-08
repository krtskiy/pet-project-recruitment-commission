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
VALUES (DEFAULT, 'Адмін', 'Адмін', 'Адмінович', 'admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', 'адмін', 'адмін',
        'адмін', 1, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Денис', 'Денисов', 'Денисович', 'user@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Київська обл.',
        'Київ', 'ЗОШ №10', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Віталій', 'Волочай', 'Віталієвич', 'user1@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Харківська обл.', 'Харків', 'ЗОШ №11', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Сергій', 'Сергієнко', 'Сергійович', 'user2@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Луганська обл.', 'Луганськ', 'ЗОШ №12', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Володимир', 'Володимиров', 'Володимирович', 'user3@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Донецька обл.', 'Донецьк', 'ЗОШ №13', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Артем', 'Артемов', 'Артемович', 'user4@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Черкаська обл.', 'Черкаси', 'ЗОШ №14', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Данило', 'Данилов', 'Данилович', 'user5@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Полтавська обл.', 'Полтава', 'ЗОШ №15', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Олександр', 'Олександров', 'Олександрович', 'user6@user.com', 'ee11cbb19052e40b07aac0ca060c23ee',
        'Одеська обл.', 'Одеса', 'ЗОШ №16', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Євген', 'Євгенов', 'Євгенович', 'user7@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Сумська обл.',
        'Суми', 'ЗОШ №17', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Алла', 'Аллова', 'Аллівна', 'user8@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Запорізька обл.',
        'Запоріжжя', 'ЗОШ №18', DEFAULT, DEFAULT);
INSERT INTO users
VALUES (DEFAULT, 'Аліна', 'Алінова', 'Алінівна', 'user9@user.com', 'ee11cbb19052e40b07aac0ca060c23ee', 'Львівська обл.',
        'Львів', 'ЗОШ №19', DEFAULT, DEFAULT);



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



