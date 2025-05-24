-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce` ;
USE `ecommerce` ;

-- -----------------------------------------------------
-- Table `ecommerce`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(255) NOT NULL,
  `permission` INT(11) NOT NULL,
  `api_key` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `is_validated` BIT(1) NOT NULL DEFAULT 0,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 21;


-- -----------------------------------------------------
-- Table `ecommerce`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`categorias` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  `users_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_categorias_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_categorias_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `ecommerce`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`productos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `referencia` VARCHAR(25) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  `cantidad` INT(11) NOT NULL DEFAULT 0,
  `precio` INT(11) NOT NULL DEFAULT 0,
  `categorias_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_productos_categorias_idx` (`categorias_id` ASC) VISIBLE,
  CONSTRAINT `fk_productos_categorias`
    FOREIGN KEY (`categorias_id`)
    REFERENCES `ecommerce`.`categorias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`pedidos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `direccion` VARCHAR(250) NOT NULL,
  `total` INT(11) NOT NULL,
  `users_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_pedidos_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedidos_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `ecommerce`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`linea_pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`linea_pedidos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT(11) NOT NULL,
  `precio` INT(11) NOT NULL,
  `productos_id` INT(11) NOT NULL,
  `pedidos_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_linea_pedidos_productos1_idx` (`productos_id` ASC) VISIBLE,
  INDEX `fk_linea_pedidos_pedidos1_idx` (`pedidos_id` ASC) VISIBLE,
  CONSTRAINT `fk_linea_pedidos_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `ecommerce`.`productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_linea_pedidos_pedidos1`
    FOREIGN KEY (`pedidos_id`)
    REFERENCES `ecommerce`.`pedidos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`productos_documentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce`.`productos_documentos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(500) NOT NULL,
  `user_name` VARCHAR(500) NOT NULL,
  `productos_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_productos_documentos_productos1_idx` (`productos_id` ASC) VISIBLE,
  CONSTRAINT `fk_productos_documentos_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `ecommerce`.`productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
