-- MySQL Script generated by MySQL Workbench
-- Thu May 13 21:33:13 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema batallas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `batallas` ;

-- -----------------------------------------------------
-- Schema batallas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `batallas` DEFAULT CHARACTER SET utf8 ;
USE `batallas` ;

-- -----------------------------------------------------
-- Table `batallas`.`race`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`race` ;

CREATE TABLE IF NOT EXISTS `batallas`.`race` (
  `race_id` INT NOT NULL,
  `race_name` VARCHAR(200) NULL,
  `hp` INT NULL,
  `strength` INT NULL,
  `speed` INT NULL,
  `agility` INT NULL,
  `defense` INT NULL,
  PRIMARY KEY (`race_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`warriors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`warriors` ;

CREATE TABLE IF NOT EXISTS `batallas`.`warriors` (
  `warrior_id` INT NOT NULL,
  `warrior_name` VARCHAR(200) NULL,
  `warrior_image_path` VARCHAR(200) NULL,
  `race_id` INT NULL,
  PRIMARY KEY (`warrior_id`),
  INDEX `race_id_idx` (`race_id` ASC) VISIBLE,
  CONSTRAINT `race_id`
    FOREIGN KEY (`race_id`)
    REFERENCES `batallas`.`race` (`race_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`players` ;

CREATE TABLE IF NOT EXISTS `batallas`.`players` (
  `player_id` INT NOT NULL,
  `player_name` VARCHAR(200) NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`ranking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`ranking` ;

CREATE TABLE IF NOT EXISTS `batallas`.`ranking` (
  `player_id` INT NULL AUTO_INCREMENT,
  `total_points` INT NULL,
  `warrior_id` INT NULL,
  INDEX `player_id_idx` (`player_id` ASC) VISIBLE,
  INDEX `warrior_id_idx` (`warrior_id` ASC) VISIBLE,
  CONSTRAINT `player_id`
    FOREIGN KEY (`player_id`)
    REFERENCES `batallas`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `warrior_id`
    FOREIGN KEY (`warrior_id`)
    REFERENCES `batallas`.`warriors` (`warrior_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`weapons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`weapons` ;

CREATE TABLE IF NOT EXISTS `batallas`.`weapons` (
  `weapon_id` INT NOT NULL,
  `weapon_name` VARCHAR(200) NULL,
  `weapon_image_path` VARCHAR(200) NULL,
  `strength` INT NULL,
  `speed` INT NULL,
  `weapon_race` VARCHAR(200) NULL,
  PRIMARY KEY (`weapon_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`battles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`battles` ;

CREATE TABLE IF NOT EXISTS `batallas`.`battles` (
  `battle_id` INT NOT NULL,
  `player_id` INT NULL,
  `warrior_id` INT NULL,
  `weapon_id` INT NULL,
  `opponent_id` INT NULL,
  `opponent_weapon_id` INT NULL,
  `injuries_caused` INT NULL,
  `injuries_suffered` INT NULL,
  `battle_points` INT NULL,
  PRIMARY KEY (`battle_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- -------------------------------------------
-- Populate BBDD
-- -------------------------------------------
use batallas;

/* Insercion de datos a tabla race*/

insert into race values ( 1, "Human", 50, 5, 5, 6, 3);
insert into race values ( 2, "Elf", 40, 4, 7, 7, 2);
insert into race values ( 3, "Dwarf", 60, 6, 3, 5, 4);

/* Insercion de datos a tabla warriors*/

insert into warriors values ( 1, "NombreGuerrero1", "c://path/imagenes/humano01", 1);
insert into warriors values ( 2, "NombreGuerrero2", "c://path/imagenes/humano02", 1);
insert into warriors values ( 3, "NombreGuerrero3", "c://path/imagenes/humano03", 1);
insert into warriors values ( 4, "NombreGuerrero4", "c://path/imagenes/elfo01", 2);
insert into warriors values ( 5, "NombreGuerrero5", "c://path/imagenes/elfo02", 2);
insert into warriors values ( 6, "NombreGuerrero6", "c://path/imagenes/elfo03", 2);
insert into warriors values ( 7, "NombreGuerrero7", "c://path/imagenes/enano01", 3);
insert into warriors values ( 8, "NombreGuerrero8", "c://path/imagenes/enano02", 3);
insert into warriors values ( 9, "NombreGuerrero9", "c://path/imagenes/enano03", 3);

/* Insercion de datos a tabla weapons*/

insert into weapons values ( 1, "Dagger", "c://path/imagenes/daga", 3, 0, "Human,Elf");
insert into weapons values ( 2, "Sword", "c://path/imagenes/Espada", 1, 1, "Human,Elf,Dwarf");
insert into weapons values ( 3, "Axe", "c://path/imagenes/Hacha", 3, 0, "human,Dwarf");
insert into weapons values ( 4, "DoubleSwords", "c://path/imagenes/Doubles", 2, 2, "Human,Elf");
insert into weapons values ( 5, "Scimitar", "c://path/imagenes/Cimitarra", 2, 1, "Human,Elf");
insert into weapons values ( 6, "Bow", "c://path/imagenes/Arco", 5, 1, "Elf");
insert into weapons values ( 7, "Katana", "c://path/imagenes/Katana", 3, 2, "Human");
insert into weapons values ( 8, "Knife", "c://path/imagenes/Cuchillo", 4, 0, "Human,Elf,Dwarf");
insert into weapons values ( 9, "Great Axe", "c://path/imagenes/Gran_hacha", 5, 0, "Dwarf");


