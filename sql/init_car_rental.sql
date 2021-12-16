-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema car_rental_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `car_rental_db` ;

-- -----------------------------------------------------
-- Schema car_rental_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `car_rental_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `car_rental_db` ;

-- -----------------------------------------------------
-- Table `car_rental_db`.`car_mark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`car_mark` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`car_mark` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `car_rental_db`.`quality_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`quality_class` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`quality_class` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `car_rental_db`.`car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`car` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `car_name` VARCHAR(45) NOT NULL,
  `car_cost` DECIMAL(9,2) UNSIGNED NOT NULL,
  `class_id` INT NOT NULL,
  `mark_id` INT NOT NULL,
  `car_status` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_car_car_mark1_idx` (`mark_id` ASC) VISIBLE,
  INDEX `fk_car_quality_class1_idx` (`class_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_car_mark1`
    FOREIGN KEY (`mark_id`)
    REFERENCES `car_rental_db`.`car_mark` (`id`),
  CONSTRAINT `fk_car_quality_class1`
    FOREIGN KEY (`class_id`)
    REFERENCES `car_rental_db`.`quality_class` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `car_rental_db`.`receipt_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`receipt_status` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`receipt_status` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `car_rental_db`.`rent_option`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`rent_option` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`rent_option` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `car_rental_db`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`user_role` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`user_role` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `car_rental_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`user` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `user_surname` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  `user_status` TINYINT NOT NULL,
  `user_balance` DECIMAL(9,2) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_user_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `car_rental_db`.`user_role` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `car_rental_db`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_rental_db`.`receipt` ;

CREATE TABLE IF NOT EXISTS `car_rental_db`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `car_id` INT NOT NULL,
  `passport` INT NOT NULL,
  `option_id` INT NOT NULL,
  `rent_duration` DATE NOT NULL,
  `status_id` INT NOT NULL,
  `receipt_comm` VARCHAR(255) NULL DEFAULT NULL,
  `bill_cost` DECIMAL(9,2) UNSIGNED ZEROFILL NOT NULL,
  `remont_bill` DECIMAL(9,2) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `passport_UNIQUE` (`passport` ASC) VISIBLE,
  INDEX `fk_user_has_car_car1_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_user_has_car_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_receipt_rent_option1_idx` (`option_id` ASC) VISIBLE,
  INDEX `fk_receipt_receipt_status2_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_receipt_status2`
    FOREIGN KEY (`status_id`)
    REFERENCES `car_rental_db`.`receipt_status` (`id`),
  CONSTRAINT `fk_receipt_rent_option1`
    FOREIGN KEY (`option_id`)
    REFERENCES `car_rental_db`.`rent_option` (`id`),
  CONSTRAINT `fk_user_has_car_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_rental_db`.`car` (`id`),
  CONSTRAINT `fk_user_has_car_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `car_rental_db`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
