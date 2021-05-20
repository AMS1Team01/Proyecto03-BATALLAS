
/*SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
*/
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
/* In this table we did not implemet AUTO_INCREMENT because we need to control some program restrictions related to this primary key, 
 and we prefered to assign this value manually */
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
  `warrior_id` INT NOT NULL AUTO_INCREMENT,
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
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `player_name` VARCHAR(200) NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `batallas`.`ranking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `batallas`.`ranking` ;

CREATE TABLE IF NOT EXISTS `batallas`.`ranking` (
  `player_id` INT NOT NULL,
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
  `weapon_id` INT NOT NULL AUTO_INCREMENT,
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
  `battle_id` INT NOT NULL AUTO_INCREMENT,
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

/*
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
*/
-- -------------------------------------------
-- Populate BBDD
-- -------------------------------------------
use batallas;

/* Insercion de datos a tabla race*/

insert into race values ( 1, "Human", 50, 5, 5, 6, 3);
insert into race values ( 2, "Elf", 40, 4, 7, 7, 2);
insert into race values ( 3, "Dwarf", 60, 6, 3, 5, 4);

/* Insercion de datos a tabla warriors*/

insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Brenna", "Warriors/Human1.png", 1);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Ikhram", "Warriors/Human2.png", 1);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Arzzet", "Warriors/Human3.png", 1);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Elden", "Warriors/Elf1.png", 2);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Ageha", "Warriors/Elf2.png", 2);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Hellenia", "Warriors/Elf3.png", 2);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Pelor", "Warriors/Dwarf1.png", 3);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Groth", "Warriors/Dwarf2.png", 3);
insert into warriors (warrior_name, warrior_image_path, race_id) values ( "Grumli", "Warriors/Dwarf3.png", 3);

/* Insercion de datos a tabla weapons*/

insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Dagger", "Weapons/Dagger.png", 0, 3, "Human,Elf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Sword", "Weapons/Sword.png", 1, 1, "Human,Elf,Dwarf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Axe", "Weapons/Axe.png", 3, 0, "human,Dwarf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Double Swords", "Weapons/DoubleSwords.png", 2, 2, "Human,Elf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Scimitar", "Weapons/Scimitar.png", 1, 2, "Human,Elf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Bow", "Weapons/Bow.png", 1, 5, "Elf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Katana", "Weapons/Katana.png", 2, 3, "Human");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Knife", "Weapons/Knife.png", 0, 4, "Human,Elf,Dwarf");
insert into weapons (weapon_name, weapon_image_path, strength, speed, weapon_race) values ( "Great Axe", "Weapons/GreatAxe.png", 5, 0, "Dwarf");

