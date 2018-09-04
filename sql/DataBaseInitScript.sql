-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`authorities` ;

CREATE TABLE IF NOT EXISTS `library`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `authority_name` VARCHAR(20) NOT NULL,
  `reader_flag` TINYINT(1) NOT NULL DEFAULT '0',
  `admin_flag` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`books` ;

CREATE TABLE IF NOT EXISTS `library`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `short_title` VARCHAR(100) NOT NULL,
  `full_title` VARCHAR(300) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `keywords` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`shelves`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`shelves` ;

CREATE TABLE IF NOT EXISTS `library`.`shelves` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `adress` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`users` ;

CREATE TABLE IF NOT EXISTS `library`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `firstName` VARCHAR(30) NULL DEFAULT NULL,
  `lastName` VARCHAR(30) NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`user_authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user_authority` ;

CREATE TABLE IF NOT EXISTS `library`.`user_authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `FK_USERAUTHORITY_idx` (`user_id` ASC, `authority_id` ASC),
  INDEX `FK_USER_AUTHORITY_idx` (`user_id` ASC),
  INDEX `FK_AUTHORITY_idx` (`authority_id` ASC),
  CONSTRAINT `FK_AUTHORITY`
  FOREIGN KEY (`authority_id`)
  REFERENCES `library`.`authorities` (`id`),
  CONSTRAINT `FK_USER_AUTHORITY`
  FOREIGN KEY (`user_id`)
  REFERENCES `library`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`user_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user_book` ;

CREATE TABLE IF NOT EXISTS `library`.`user_book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `book_id` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC, `book_id` ASC),
  CONSTRAINT `user_id_in_user_book`
  FOREIGN KEY (`user_id`)
  REFERENCES `library`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_id_in_user_book`
  FOREIGN KEY (`book_id`)
  REFERENCES `library`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`shelf_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`shelf_book` ;

CREATE TABLE IF NOT EXISTS `library`.`shelf_book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `shelf_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `shelf_id_idx` (`shelf_id` ASC),
  INDEX `book_id_idx` (`book_id` ASC),
  CONSTRAINT `shelf_id_in_shelf_book`
  FOREIGN KEY (`shelf_id`)
  REFERENCES `library`.`shelves` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_id_in_shelf_book`
  FOREIGN KEY (`book_id`)
  REFERENCES `library`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`authors` ;

CREATE TABLE IF NOT EXISTS `library`.`authors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(60) NULL,
  `details` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`book_author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`book_author` ;

CREATE TABLE IF NOT EXISTS `library`.`book_author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC),
  INDEX `book_id_idx` (`book_id` ASC),
  CONSTRAINT `book_id`
  FOREIGN KEY (`book_id`)
  REFERENCES `library`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `author_id`
  FOREIGN KEY (`author_id`)
  REFERENCES `library`.`authors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `library`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`authorities` (`id`, `authority_name`, `reader_flag`, `admin_flag`) VALUES (1, 'reader', 1, 0);
INSERT INTO `library`.`authorities` (`id`, `authority_name`, `reader_flag`, `admin_flag`) VALUES (2, 'admin', 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`books`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`books` (`id`, `short_title`, `full_title`, `description`, `keywords`) VALUES (1, 'The Art of Computer Programming', 'The Art of Computer Programming, Volumes 1-4A Boxed Set 1st Edition', 'The bible of all fundamental algorithms and the work that taught many of today’s software developers most of what they know about computer programming.', 'Computer Programming Knuth');
INSERT INTO `library`.`books` (`id`, `short_title`, `full_title`, `description`, `keywords`) VALUES (2, 'Innovation and Its Enemies: Why People Resist New Technologies', 'Innovation and Its Enemies: Why People Resist New Technologies 1st Edition', 'Explains the roots of resistance to new technologies and why such resistance is not always futile. Draws on nearly 600 years of economic history to show how the balance of winners and losers shapes technological controversies. Outlines policy strategies for inclusive innovation to reduce the risks and maximize the benefits of new technologies.', 'Technologies Innovation Resistance Calestous Juma');
INSERT INTO `library`.`books` (`id`, `short_title`, `full_title`, `description`, `keywords`) VALUES (3, 'Originals: How Non-Conformists Move the World', 'Originals: How Non-Conformists Move the World Hardcover – February 2, 2016', 'Author addresses the challenge of improving the world from the perspective of becoming original: choosing to champion novel ideas and values that go against the grain, battle conformity, and buck outdated traditions. How can we originate new ideas, policies, and practices without risking it all.', 'Grant Originals Sandberg');
INSERT INTO `library`.`books` (`id`, `short_title`, `full_title`, `description`, `keywords`) VALUES (4, 'How Google Works', 'How Google Works Hardcover – Unabridged, September 23, 2014', 'Seasoned Google execs Eric Schmidt and Jonathan Rosenberg provide an insider\'s guide to Google-from the business history and corporate strategy to developing a new management philosophy and creating a workplace culture where innovation and creativity thrive.', 'Google Insiders Schmidt Rosenberg');
INSERT INTO `library`.`books` (`id`, `short_title`, `full_title`, `description`, `keywords`) VALUES (5, 'Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future', 'Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future Paperback – January 24, 2017', 'In the spirit of \"Steve Jobs\" and \"Moneyball\", \"Elon Musk\" is both an illuminating and authorized look at the extraordinary life of one of Silicon Valley\'s most exciting, unpredictable, and ambitious entrepreneurs — a real-life Tony Stark — and a fascinating exploration of the renewal of American invention and its new \"makers\".', 'Tesla SpaceX Elon Musk Moneyball Silicon Valley');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`shelves`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`shelves` (`id`, `name`, `adress`, `description`) VALUES (1, 'sciences and technologies', 'A1', 'science and technology contexts');
INSERT INTO `library`.`shelves` (`id`, `name`, `adress`, `description`) VALUES (2, 'social works', 'B1', 'social,and philosophical contexts');
INSERT INTO `library`.`shelves` (`id`, `name`, `adress`, `description`) VALUES (3, 'historical works', 'C1', 'historical contexts');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`users` (`id`, `login`, `password`, `email`, `firstName`, `lastName`, `phone`) VALUES (1, 'root', 'root', 'root@gmail.com', 'Andrii', 'Kuriata', '+380978833743');
INSERT INTO `library`.`users` (`id`, `login`, `password`, `email`, `firstName`, `lastName`, `phone`) VALUES (2, 'Andrii', 'birthDate', 'andrii.user@gmail.com', 'Anonimous', 'User', '+380632984112');
INSERT INTO `library`.`users` (`id`, `login`, `password`, `email`, `firstName`, `lastName`, `phone`) VALUES (3, 'Roman', 'superPass', 'romeo@me.ua', 'Roman', 'Ivankov', '+380951122456');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`user_authority`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`user_authority` (`id`, `user_id`, `authority_id`) VALUES (1, 1, 1);
INSERT INTO `library`.`user_authority` (`id`, `user_id`, `authority_id`) VALUES (2, 1, 2);
INSERT INTO `library`.`user_authority` (`id`, `user_id`, `authority_id`) VALUES (3, 2, 1);
INSERT INTO `library`.`user_authority` (`id`, `user_id`, `authority_id`) VALUES (4, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`user_book`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`user_book` (`id`, `user_id`, `book_id`, `date`) VALUES (1, 1, 5, '2008-11-11');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`shelf_book`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`shelf_book` (`id`, `shelf_id`, `book_id`, `quantity`) VALUES (1, 1, 1, 8);
INSERT INTO `library`.`shelf_book` (`id`, `shelf_id`, `book_id`, `quantity`) VALUES (2, 1, 2, 4);
INSERT INTO `library`.`shelf_book` (`id`, `shelf_id`, `book_id`, `quantity`) VALUES (3, 1, 3, 2);
INSERT INTO `library`.`shelf_book` (`id`, `shelf_id`, `book_id`, `quantity`) VALUES (4, 1, 4, 1);
INSERT INTO `library`.`shelf_book` (`id`, `shelf_id`, `book_id`, `quantity`) VALUES (5, 1, 5, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`authors`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (1, 'Donald Ervin Knuth', 'USA');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (2, 'Calestous Juma', 'Republic of Kenya');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (3, 'Adam Grant', 'USA');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (4, 'Sheryl Sandberg', 'USA');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (5, 'Eric Schmidt', 'USA');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (6, 'Jonathan Rosenberg', 'USA');
INSERT INTO `library`.`authors` (`id`, `full_name`, `details`) VALUES (7, 'Ashlee Vance', 'USA');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`book_author`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (1, 1, 1);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (2, 2, 2);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (3, 3, 3);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (4, 3, 4);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (5, 4, 5);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (6, 4, 6);
INSERT INTO `library`.`book_author` (`id`, `book_id`, `author_id`) VALUES (7, 5, 7);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
