CREATE TABLE `files`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NULL,
    `filePath` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `users`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `events`
(
    `id`      INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NULL,
    `file_id` INT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`),
    FOREIGN KEY (`file_id`) REFERENCES files(`id`)
);
