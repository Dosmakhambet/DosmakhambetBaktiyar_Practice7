CREATE TABLE `file`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NULL,
    `filePath` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE event
(
    `id`      INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NULL,
    `file_id` INT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`),
    FOREIGN KEY (`file_id`) REFERENCES file(`id`)
);
