-- MySQL Script generated by MySQL Workbench
-- 04/11/17 18:29:24
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sistema_comision
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sistema_comision` ;

-- -----------------------------------------------------
-- Schema sistema_comision
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema_comision` DEFAULT CHARACTER SET utf8 ;
USE `sistema_comision` ;

-- -----------------------------------------------------
-- Table `sistema_comision`.`perfil_usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`perfil_usuarios` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`perfil_usuarios` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `descripcion` VARCHAR(300) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_comision`.`direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`direccion` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`direccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(200) NULL,
  `sector` VARCHAR(200) NULL,
  `ciudad` VARCHAR(200) NULL,
  `provincia` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_comision`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`persona` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`persona` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NULL,
  `apellido` VARCHAR(200) NULL,
  `documento_identidad` VARCHAR(100) NULL,
  `sexo` CHAR(1) NULL,
  `correo` VARCHAR(100) NULL,
  `celular` VARCHAR(45) NULL,
  `direccion_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_persona_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `sistema_comision`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_persona_direccion1_idx` ON `sistema_comision`.`persona` (`direccion_id` ASC);

CREATE INDEX `nombre` ON `sistema_comision`.`persona` (`nombre` ASC);

CREATE INDEX `apellido` ON `sistema_comision`.`persona` (`apellido` ASC);

CREATE FULLTEXT INDEX `nombre_completo` ON `sistema_comision`.`persona` (`nombre` ASC, `apellido` ASC);

CREATE INDEX `document_identidad` ON `sistema_comision`.`persona` (`documento_identidad` ASC);


-- -----------------------------------------------------
-- Table `sistema_comision`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contrasenia` VARCHAR(200) NULL,
  `desactivado` TINYINT(1) NULL,
  `persona_id` INT NOT NULL,
  `perfil_usuarios_Id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuarios_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `sistema_comision`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_perfil_usuarios1`
    FOREIGN KEY (`perfil_usuarios_Id`)
    REFERENCES `sistema_comision`.`perfil_usuarios` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_usuarios_persona1_idx` ON `sistema_comision`.`usuarios` (`persona_id` ASC);

CREATE INDEX `fk_usuarios_perfil_usuarios1_idx` ON `sistema_comision`.`usuarios` (`perfil_usuarios_Id` ASC);


-- -----------------------------------------------------
-- Table `sistema_comision`.`tipo_inmuebles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`tipo_inmuebles` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`tipo_inmuebles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NULL,
  `descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_comision`.`moneda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`moneda` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`moneda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `simbolo` VARCHAR(20) NULL,
  `tasa` DECIMAL NULL,
  `es_principal` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_comision`.`inmuebles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`inmuebles` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`inmuebles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `detalles` VARCHAR(1000) NULL,
  `precio` DECIMAL NULL,
  `superficie` VARCHAR(50) NULL,
  `dormitorios` INT NULL,
  `tipo_inmuebles_id` INT NOT NULL,
  `direccion_id` INT NOT NULL,
  `moneda_id` INT NOT NULL,
  `comision_venta` DECIMAL NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inmuebles_tipo_inmuebles1`
    FOREIGN KEY (`tipo_inmuebles_id`)
    REFERENCES `sistema_comision`.`tipo_inmuebles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmuebles_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `sistema_comision`.`direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmuebles_moneda1`
    FOREIGN KEY (`moneda_id`)
    REFERENCES `sistema_comision`.`moneda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_inmuebles_tipo_inmuebles1_idx` ON `sistema_comision`.`inmuebles` (`tipo_inmuebles_id` ASC);

CREATE INDEX `fk_inmuebles_direccion1_idx` ON `sistema_comision`.`inmuebles` (`direccion_id` ASC);

CREATE INDEX `fk_inmuebles_moneda1_idx` ON `sistema_comision`.`inmuebles` (`moneda_id` ASC);


-- -----------------------------------------------------
-- Table `sistema_comision`.`ventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`ventas` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`ventas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuarios_id` INT NOT NULL,
  `moneda_id` INT NOT NULL,
  `persona_id` INT NOT NULL,
  `precio` DECIMAL NULL,
  `inmuebles_id` INT NOT NULL,
  PRIMARY KEY (`id`, `inmuebles_id`),
  CONSTRAINT `fk_ventas_usuarios1`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `sistema_comision`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_inmuebles1`
    FOREIGN KEY (`id`)
    REFERENCES `sistema_comision`.`inmuebles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_moneda1`
    FOREIGN KEY (`moneda_id`)
    REFERENCES `sistema_comision`.`moneda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `sistema_comision`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_inmuebles2`
    FOREIGN KEY (`inmuebles_id`)
    REFERENCES `sistema_comision`.`inmuebles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ventas_usuarios1_idx` ON `sistema_comision`.`ventas` (`usuarios_id` ASC);

CREATE INDEX `fk_ventas_inmuebles1_idx` ON `sistema_comision`.`ventas` (`id` ASC);

CREATE INDEX `fk_ventas_moneda1_idx` ON `sistema_comision`.`ventas` (`moneda_id` ASC);

CREATE INDEX `fk_ventas_persona1_idx` ON `sistema_comision`.`ventas` (`persona_id` ASC);

CREATE INDEX `fk_ventas_inmuebles2_idx` ON `sistema_comision`.`ventas` (`inmuebles_id` ASC);


-- -----------------------------------------------------
-- Table `sistema_comision`.`auditoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistema_comision`.`auditoria` ;

CREATE TABLE IF NOT EXISTS `sistema_comision`.`auditoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tabla` VARCHAR(45) NULL,
  `registro_id` INT NOT NULL,
  `action` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

USE `sistema_comision`;

DELIMITER $$

USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`usuarios_AFTER_INSERT` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`usuarios_AFTER_INSERT` AFTER INSERT ON `usuarios` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`) values("usuarios", NEW.id, "insert");
END$$


USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`usuarios_AFTER_UPDATE` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`usuarios_AFTER_UPDATE` AFTER UPDATE ON `usuarios` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`)  values("usuarios", OLD.id, "update");
END$$


USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`usuarios_BEFORE_DELETE` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`usuarios_BEFORE_DELETE` BEFORE DELETE ON `usuarios` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`)  values("usuarios", OLD.id, "delete");
END$$


USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`ventas_AFTER_INSERT` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`ventas_AFTER_INSERT` AFTER INSERT ON `ventas` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`)  values("ventas", NEW.id, "insert");
END$$


USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`ventas_AFTER_UPDATE` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`ventas_AFTER_UPDATE` AFTER UPDATE ON `ventas` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`)  values("ventas", NEW.id, "update");
END$$


USE `sistema_comision`$$
DROP TRIGGER IF EXISTS `sistema_comision`.`ventas_AFTER_DELETE` $$
USE `sistema_comision`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistema_comision`.`ventas_AFTER_DELETE` AFTER DELETE ON `ventas` FOR EACH ROW
BEGIN
INSERT INTO `auditoria` (`tabla`, `registro_id`, `action`)  values("ventas", OLD.id, "delete");
END$$


DELIMITER ;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO usuario_sistemas_comisiones;
 DROP USER usuario_sistemas_comisiones;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'usuario_sistemas_comisiones' IDENTIFIED BY '123456';

GRANT ALL ON `sistema_comision`.* TO 'usuario_sistemas_comisiones';
GRANT SELECT ON TABLE `sistema_comision`.* TO 'usuario_sistemas_comisiones';
GRANT SELECT, INSERT, TRIGGER ON TABLE `sistema_comision`.* TO 'usuario_sistemas_comisiones';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `sistema_comision`.* TO 'usuario_sistemas_comisiones';
GRANT EXECUTE ON ROUTINE `sistema_comision`.* TO 'usuario_sistemas_comisiones';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
