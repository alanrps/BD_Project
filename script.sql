-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcionario` (
  `Id` INT NOT NULL,
  `CPF` VARCHAR(14) NULL,
  `Nome` VARCHAR(100) NULL,
  `Turno` VARCHAR(20) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cobrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cobrador` (
  `Id_Cobrador` INT NOT NULL,
  PRIMARY KEY (`Id_Cobrador`),
  CONSTRAINT `fk_Cobrador_Funcionario1`
    FOREIGN KEY (`Id_Cobrador`)
    REFERENCES `mydb`.`Funcionario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Motorista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Motorista` (
  `NroCNH` VARCHAR(11) NULL,
  `Id_Motorista` INT NOT NULL,
  PRIMARY KEY (`Id_Motorista`),
  INDEX `fk_Motorista_Funcionario1_idx` (`Id_Motorista` ASC),
  CONSTRAINT `fk_Motorista_Funcionario1`
    FOREIGN KEY (`Id_Motorista`)
    REFERENCES `mydb`.`Funcionario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Onibus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Onibus` (
  `Placa` CHAR(7) NOT NULL,
  `Data_Fabr` DATE NULL,
  `Inicio_Oper` DATE NULL,
  PRIMARY KEY (`Placa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Linha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Linha` (
  `Id_Linha` INT NOT NULL,
  `Nome_Linha` VARCHAR(100) NULL,
  PRIMARY KEY (`Id_Linha`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Viagem` (
  `NroViagem` INT NOT NULL,
  `Data_e_Horario` DATETIME NULL,
  `NroEntradas` INT NULL,
  `Id_Cobrador` INT NOT NULL,
  `Id_Motorista` INT NOT NULL,
  `Placa_Onibus` CHAR(7) NOT NULL,
  `Id_Linha` INT NOT NULL,
  PRIMARY KEY (`NroViagem`, `Id_Cobrador`, `Id_Motorista`, `Placa_Onibus`, `Id_Linha`),
  INDEX `fk_Viagem_Cobrador1_idx` (`Id_Cobrador` ASC),
  INDEX `fk_Viagem_Motorista1_idx` (`Id_Motorista` ASC),
  INDEX `fk_Viagem_Onibus1_idx` (`Placa_Onibus` ASC),
  INDEX `fk_Viagem_Linha1_idx` (`Id_Linha` ASC),
  CONSTRAINT `fk_Viagem_Cobrador1`
    FOREIGN KEY (`Id_Cobrador`)
    REFERENCES `mydb`.`Cobrador` (`Id_Cobrador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viagem_Motorista1`
    FOREIGN KEY (`Id_Motorista`)
    REFERENCES `mydb`.`Motorista` (`Id_Motorista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viagem_Onibus1`
    FOREIGN KEY (`Placa_Onibus`)
    REFERENCES `mydb`.`Onibus` (`Placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viagem_Linha1`
    FOREIGN KEY (`Id_Linha`)
    REFERENCES `mydb`.`Linha` (`Id_Linha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rua`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rua` (
  `Id_Rua` INT NOT NULL,
  `NomeRua` VARCHAR(100) NULL,
  PRIMARY KEY (`Id_Rua`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bairro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bairro` (
  `Id_Bairro` INT NOT NULL,
  `Nome_Bairro` VARCHAR(100) NULL,
  PRIMARY KEY (`Id_Bairro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ponto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ponto` (
  `Id_Ponto` INT NOT NULL,
  `Id_Rua` INT NOT NULL,
  `Id_Bairro` INT NOT NULL,
  PRIMARY KEY (`Id_Ponto`, `Id_Rua`, `Id_Bairro`),
  INDEX `fk_Ponto_Rua1_idx` (`Id_Rua` ASC),
  INDEX `fk_Ponto_Bairro1_idx` (`Id_Bairro` ASC),
  CONSTRAINT `fk_Ponto_Rua1`
    FOREIGN KEY (`Id_Rua`)
    REFERENCES `mydb`.`Rua` (`Id_Rua`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ponto_Bairro1`
    FOREIGN KEY (`Id_Bairro`)
    REFERENCES `mydb`.`Bairro` (`Id_Bairro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Linha_has_Ponto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Linha_has_Ponto` (
  `Id_Linha` INT NOT NULL,
  `Id_Ponto` INT NOT NULL,
  PRIMARY KEY (`Id_Linha`, `Id_Ponto`),
  INDEX `fk_Linha_has_Ponto_Ponto1_idx` (`Id_Ponto` ASC),
  INDEX `fk_Linha_has_Ponto_Linha1_idx` (`Id_Linha` ASC),
  CONSTRAINT `fk_Linha_has_Ponto_Linha1`
    FOREIGN KEY (`Id_Linha`)
    REFERENCES `mydb`.`Linha` (`Id_Linha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Linha_has_Ponto_Ponto1`
    FOREIGN KEY (`Id_Ponto`)
    REFERENCES `mydb`.`Ponto` (`Id_Ponto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
