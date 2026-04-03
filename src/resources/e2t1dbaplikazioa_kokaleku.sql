CREATE DATABASE  IF NOT EXISTS `e2t1dbaplikazioa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `e2t1dbaplikazioa`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: e2t1dbaplikazioa
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `kokaleku`
--

DROP TABLE IF EXISTS `kokaleku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kokaleku` (
  `ID` int NOT NULL,
  `HELBIDEA` varchar(255) NOT NULL,
  `POSTAKODEA` varchar(20) DEFAULT NULL,
  `UDALERRIA` varchar(50) NOT NULL,
  `PROBINTZIA` varchar(50) DEFAULT NULL,
  `ID_HERRIALDE` char(2) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKID_HERRIALDE` (`ID_HERRIALDE`),
  CONSTRAINT `FKID_HERRIALDE` FOREIGN KEY (`ID_HERRIALDE`) REFERENCES `herrialde` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kokaleku`
--

LOCK TABLES `kokaleku` WRITE;
/*!40000 ALTER TABLE `kokaleku` DISABLE KEYS */;
INSERT INTO `kokaleku` VALUES (1,'1297 Via Cola di Rie','00989','Roma',NULL,'IT'),(2,'93091 Calle della Testa','10934','Venice',NULL,'IT'),(3,'2017 Shinjuku-ku','1689','Tokyo','Tokyo Prefecture','JP'),(4,'9450 Kamiya-cho','6823','Hiroshima',NULL,'JP'),(5,'2014 Jabberwocky Rd','26192','Southlake','Texas','US'),(6,'2011 Interiors Blvd','99236','South San Francisco','California','US'),(7,'2007 Zagora St','50090','South Brunswick','New Jersey','US'),(8,'2004 Charade Rd','98199','Seattle','Washington','US'),(9,'147 Spadina Ave','M5V 2L7','Toronto','Ontario','CA'),(10,'6092 Boxwood St','YSW 9T2','Whitehorse','Yukon','CA'),(11,'40-5-12 Laogianggen','190518','Beijing',NULL,'CN'),(12,'1298 Vileparle (E)','490231','Bombay','Maharashtra','IN'),(13,'12-98 Victoria Street','2901','Sydney','New South Wales','AU'),(14,'270 Orchard Rd','238857','Singapore',NULL,'SG'),(15,'8204 Arthur St',NULL,'London',NULL,'UK'),(16,'Magdalen Centre, The Oxford Science Park','OX9 9ZB','Oxford','Oxford','UK'),(17,'9702 Chester Road','09629850293','Stretford','Manchester','UK'),(18,'Schwanthalerstr. 7031','80925','Munich','Bavaria','DE'),(19,'Rua Frei Caneca 1360 ','01307-002','Sao Paulo','Sao Paulo','BR'),(20,'20 Rue des Corps-Saints','1730','Geneva','Geneve','CH'),(21,'Murtenstrasse 921','3095','Bern','BE','CH'),(22,'Pieter Breughelstraat 837','3029SK','Utrecht','Utrecht','NL'),(23,'Mariano Escobedo 9991','11932','Mexico City','Distrito Federal,','MX'),(24,'PAJARES 34','48980','SANTURTZI','BIZKAIA','SP');
/*!40000 ALTER TABLE `kokaleku` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-03 11:32:45
