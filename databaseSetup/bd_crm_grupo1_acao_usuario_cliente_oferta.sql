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
-- Table structure for table `acao_usuario_cliente_oferta`
--

DROP TABLE IF EXISTS `acao_usuario_cliente_oferta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acao_usuario_cliente_oferta` (
  `acao_usuario_cliente_oferta_id` int(11) NOT NULL AUTO_INCREMENT,
  `acao_id` int(11) NOT NULL,
  `acao_usuario_cliente_oferta_descricao` varchar(250) DEFAULT NULL,
  `acao_usuario_cliente_oferta_data` date DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `oferta_id` int(11) NOT NULL,
  `funil_etapa_id` int(11) NOT NULL,
  PRIMARY KEY (`acao_usuario_cliente_oferta_id`,`usuario_id`,`cliente_id`,`oferta_id`,`funil_etapa_id`),
  KEY `fk_acoes_cliente_oferta_acao1_idx` (`acao_id`),
  KEY `fk_acao_usuario_cliente_oferta_usuario1_idx` (`usuario_id`),
  KEY `fk_acao_usuario_cliente_oferta_cliente_oferta1_idx` (`cliente_id`,`oferta_id`,`funil_etapa_id`),
  CONSTRAINT `fk_acao_usuario_cliente_oferta_cliente_oferta1` FOREIGN KEY (`cliente_id`, `oferta_id`, `funil_etapa_id`) REFERENCES `cliente_oferta` (`cliente_id`, `oferta_id`, `funil_etapa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_acao_usuario_cliente_oferta_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_acoes_cliente_oferta_acao1` FOREIGN KEY (`acao_id`) REFERENCES `acao` (`acao_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acao_usuario_cliente_oferta`
--

LOCK TABLES `acao_usuario_cliente_oferta` WRITE;
/*!40000 ALTER TABLE `acao_usuario_cliente_oferta` DISABLE KEYS */;
/*!40000 ALTER TABLE `acao_usuario_cliente_oferta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 13:48:34
