SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dboptica` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dboptica` ;

-- -----------------------------------------------------
-- Table `dboptica`.`marca`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `pais_origen` VARCHAR(45) NOT NULL ,
  `logo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`lente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`lente` (
  `codigo` VARCHAR(10) NOT NULL ,
  `precio` DOUBLE NOT NULL ,
  `color_cristal` VARCHAR(45) NOT NULL ,
  `color_marco` VARCHAR(45) NOT NULL ,
  `material_marco` VARCHAR(45) NOT NULL ,
  `genero` VARCHAR(45) NOT NULL ,
  `modelo` VARCHAR(45) NOT NULL ,
  `imagen` VARCHAR(45) NOT NULL ,
  `marca_id` INT NOT NULL ,
  PRIMARY KEY (`codigo`, `marca_id`) ,
  INDEX `fk_lente_marca1_idx` (`marca_id` ASC) ,
  CONSTRAINT `fk_lente_marca1`
    FOREIGN KEY (`marca_id` )
    REFERENCES `dboptica`.`marca` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`direccion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`direccion` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `region` VARCHAR(45) NOT NULL ,
  `comuna` VARCHAR(45) NOT NULL ,
  `calle` VARCHAR(45) NOT NULL ,
  `numero` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`sucursal`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`sucursal` (
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `direccion_id` INT NOT NULL ,
  PRIMARY KEY (`codigo`, `direccion_id`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) ,
  INDEX `fk_sucursal_direccion1_idx` (`direccion_id` ASC) ,
  CONSTRAINT `fk_sucursal_direccion1`
    FOREIGN KEY (`direccion_id` )
    REFERENCES `dboptica`.`direccion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`metrica`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`metrica` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `unidad_medida` VARCHAR(45) NOT NULL ,
  `valor` DOUBLE NOT NULL ,
  `fecha` DATE NOT NULL ,
  `sucursal_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `sucursal_codigo`) ,
  INDEX `fk_metrica_sucursal1_idx` (`sucursal_codigo` ASC) ,
  CONSTRAINT `fk_metrica_sucursal1`
    FOREIGN KEY (`sucursal_codigo` )
    REFERENCES `dboptica`.`sucursal` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`empleado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`empleado` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido_paterno` VARCHAR(45) NOT NULL ,
  `apellido_materno` VARCHAR(45) NOT NULL ,
  `fecha_nacimiento` VARCHAR(45) NOT NULL ,
  `sucursal_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `sucursal_codigo`) ,
  INDEX `fk_empleado_sucursal1_idx` (`sucursal_codigo` ASC) ,
  CONSTRAINT `fk_empleado_sucursal1`
    FOREIGN KEY (`sucursal_codigo` )
    REFERENCES `dboptica`.`sucursal` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`telefono`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`telefono` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `numero` VARCHAR(45) NOT NULL ,
  `empleado_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `empleado_id`) ,
  INDEX `fk_telefono_empleado1_idx` (`empleado_id` ASC) ,
  CONSTRAINT `fk_telefono_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `dboptica`.`empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`venta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`venta` (
  `id` INT NOT NULL ,
  `precio` DOUBLE NOT NULL ,
  `sucursal_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `sucursal_codigo`) ,
  INDEX `fk_venta_sucursal1_idx` (`sucursal_codigo` ASC) ,
  CONSTRAINT `fk_venta_sucursal1`
    FOREIGN KEY (`sucursal_codigo` )
    REFERENCES `dboptica`.`sucursal` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`venta_lente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`venta_lente` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `lente_codigo` VARCHAR(10) NOT NULL ,
  `venta_id` INT NOT NULL ,
  `cantidad` INT NOT NULL ,
  PRIMARY KEY (`id`, `venta_id`, `lente_codigo`) ,
  INDEX `fk_lente_has_venta_venta1_idx` (`venta_id` ASC) ,
  INDEX `fk_lente_has_venta_lente1_idx` (`lente_codigo` ASC) ,
  CONSTRAINT `fk_lente_has_venta_lente1`
    FOREIGN KEY (`lente_codigo` )
    REFERENCES `dboptica`.`lente` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lente_has_venta_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `dboptica`.`venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`boleta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`boleta` (
  `folio` VARCHAR(45) NOT NULL ,
  `precio_neto` DOUBLE NOT NULL ,
  `precio_con_iva` DOUBLE NOT NULL ,
  `costo_iva` DOUBLE NOT NULL ,
  `fecha_de_venta` DATE NOT NULL ,
  `hora_de_venta` TIME NOT NULL ,
  `venta_id` INT NOT NULL ,
  PRIMARY KEY (`folio`, `venta_id`) ,
  INDEX `fk_boleta_venta1_idx` (`venta_id` ASC) ,
  CONSTRAINT `fk_boleta_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `dboptica`.`venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dboptica`.`lente_sucursal`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dboptica`.`lente_sucursal` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `lente_codigo` VARCHAR(10) NOT NULL ,
  `sucursal_codigo` VARCHAR(45) NOT NULL ,
  `stock` INT NOT NULL ,
  PRIMARY KEY (`id`, `lente_codigo`, `sucursal_codigo`) ,
  INDEX `fk_lente_has_sucursal_sucursal1_idx` (`sucursal_codigo` ASC) ,
  INDEX `fk_lente_has_sucursal_lente1_idx` (`lente_codigo` ASC) ,
  CONSTRAINT `fk_lente_has_sucursal_lente1`
    FOREIGN KEY (`lente_codigo` )
    REFERENCES `dboptica`.`lente` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lente_has_sucursal_sucursal1`
    FOREIGN KEY (`sucursal_codigo` )
    REFERENCES `dboptica`.`sucursal` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `dboptica` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
