-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: academico3.rj.senac.br    Database: bd_crm_grupo1
-- ------------------------------------------------------
-- Server version	5.7.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dado_tipo`
--

DROP TABLE IF EXISTS `dado_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dado_tipo` (
  `dado_tipo_id` int(11) NOT NULL AUTO_INCREMENT,
  `dado_tipo_descricao` varchar(45) NOT NULL,
  `dado_tipo_status` tinyint(1) NOT NULL,
  `categoria_dado_id` int(11) NOT NULL,
  `dado_tipo_obrigatorio` tinyint(1) DEFAULT NULL,
  `dado_tipo_padrao` varchar(1) DEFAULT NULL,
  `dado_tipo_mascara` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dado_tipo_id`),
  KEY `fk_tipo_dado_categoria_dado1_idx` (`categoria_dado_id`),
  CONSTRAINT `fk_tipo_dado_categoria_dado1` FOREIGN KEY (`categoria_dado_id`) REFERENCES `categoria_dado` (`categoria_dado_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dado_tipo`
--

LOCK TABLES `dado_tipo` WRITE;
/*!40000 ALTER TABLE `dado_tipo` DISABLE KEYS */;
INSERT INTO `dado_tipo` VALUES (1,'Passaporte',1,1,1,'',NULL),(2,'CRM',1,1,1,'',NULL),(3,'Passaporte',1,2,1,'',NULL),(4,'CRM',1,2,1,'',NULL);
/*!40000 ALTER TABLE `dado_tipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 13:48:27
