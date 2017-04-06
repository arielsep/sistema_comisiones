-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistema_comision
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `direccion`
--

USE sistema_comision;

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(200) DEFAULT NULL,
  `sector` varchar(200) DEFAULT NULL,
  `ciudad` varchar(200) DEFAULT NULL,
  `provincia` varchar(200) DEFAULT NULL,
  `direccioncol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'1','2','DN','Sto Dgo',NULL),(2,'3ra','San Jose','DN','Sto Dgo',NULL),(3,'San Juan','4','DN','Sto Dgo',NULL),(4,'10','Los Jardinez','Sanpedro','SPM',NULL),(5,'24','Los Rios','DN','Sto Dgo',NULL);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inmuebles`
--

DROP TABLE IF EXISTS `inmuebles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inmuebles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalles` varchar(1000) DEFAULT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  `superficie` varchar(50) DEFAULT NULL,
  `dormitorios` int(11) DEFAULT NULL,
  `tipo_inmuebles_id` int(11) NOT NULL,
  `direccion_id` int(11) NOT NULL,
  `moneda_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inmuebles_tipo_inmuebles1_idx` (`tipo_inmuebles_id`),
  KEY `fk_inmuebles_direccion1_idx` (`direccion_id`),
  KEY `fk_inmuebles_moneda1_idx` (`moneda_id`),
  CONSTRAINT `fk_inmuebles_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmuebles_moneda1` FOREIGN KEY (`moneda_id`) REFERENCES `moneda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inmuebles_tipo_inmuebles1` FOREIGN KEY (`tipo_inmuebles_id`) REFERENCES `tipo_inmuebles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inmuebles`
--

LOCK TABLES `inmuebles` WRITE;
/*!40000 ALTER TABLE `inmuebles` DISABLE KEYS */;
INSERT INTO `inmuebles` VALUES (1,NULL,5000000,'100mt2',3,1,4,1),(2,NULL,100000,'200mt2',3,2,5,2);
/*!40000 ALTER TABLE `inmuebles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `simbolo` varchar(20) DEFAULT NULL,
  `tasa` decimal(10,0) DEFAULT NULL,
  `es_principal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,'Peso Dominicano','RD$',1,1),(2,'Dolar','USD$',48,0);
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_usuarios`
--

DROP TABLE IF EXISTS `perfil_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_usuarios`
--

LOCK TABLES `perfil_usuarios` WRITE;
/*!40000 ALTER TABLE `perfil_usuarios` DISABLE KEYS */;
INSERT INTO `perfil_usuarios` VALUES (1,'Administrador',NULL),(2,'Vendedor',NULL);
/*!40000 ALTER TABLE `perfil_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `apellido` varchar(200) DEFAULT NULL,
  `documento_identidad` varchar(100) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `direccion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_persona_direccion1_idx` (`direccion_id`),
  CONSTRAINT `fk_persona_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Ariel','Sepulveda','0019875677','M','ariel','8096765435',1),(2,'Annelisse ','Emeterio','098456765','F','annco','890545667',2),(3,'Wilman','Cepeda','00983457774','M','wilmajhk','90987765',3);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_inmuebles`
--

DROP TABLE IF EXISTS `tipo_inmuebles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_inmuebles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_inmuebles`
--

LOCK TABLES `tipo_inmuebles` WRITE;
/*!40000 ALTER TABLE `tipo_inmuebles` DISABLE KEYS */;
INSERT INTO `tipo_inmuebles` VALUES (1,'Apartamento',NULL),(2,'Casa',NULL),(3,'Solar',NULL),(4,'Local Comercial',NULL);
/*!40000 ALTER TABLE `tipo_inmuebles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(200) DEFAULT NULL,
  `desactivado` tinyint(1) DEFAULT NULL,
  `persona_id` int(11) NOT NULL,
  `perfil_usuarios_Id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuarios_persona1_idx` (`persona_id`),
  KEY `fk_usuarios_perfil_usuarios1_idx` (`perfil_usuarios_Id`),
  CONSTRAINT `fk_usuarios_perfil_usuarios1` FOREIGN KEY (`perfil_usuarios_Id`) REFERENCES `perfil_usuarios` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'e10adc3949ba59abbe56e057f20f883e',0,1,1),(2,'e10adc3949ba59abbe56e057f20f883e',0,2,2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuarios_id` int(11) NOT NULL,
  `moneda_id` int(11) NOT NULL,
  `persona_id` int(11) NOT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ventas_usuarios1_idx` (`usuarios_id`),
  KEY `fk_ventas_inmuebles1_idx` (`id`),
  KEY `fk_ventas_moneda1_idx` (`moneda_id`),
  KEY `fk_ventas_persona1_idx` (`persona_id`),
  CONSTRAINT `fk_ventas_inmuebles1` FOREIGN KEY (`id`) REFERENCES `inmuebles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_moneda1` FOREIGN KEY (`moneda_id`) REFERENCES `moneda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,2,1,3,45000000);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06  0:13:50
