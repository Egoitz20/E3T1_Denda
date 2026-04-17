-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: e2t1dbaplikazioa
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `saltzaile`
--

DROP TABLE IF EXISTS `saltzaile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saltzaile` (
  `ID` int NOT NULL,
  `ERABILTZAILEA` varchar(25) NOT NULL,
  `PASAHITZA` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ERABILTZAILEA` (`ERABILTZAILEA`),
  CONSTRAINT `FK_SALTZAILE_LANGILE` FOREIGN KEY (`ID`) REFERENCES `langile` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saltzaile`
--

LOCK TABLES `saltzaile` WRITE;
/*!40000 ALTER TABLE `saltzaile` DISABLE KEYS */;
INSERT INTO `saltzaile` VALUES (51,'pjordan','pjordan'),(52,'sowens','sowens'),(53,'sreynolds','sreynolds'),(54,'lfisher','lfisher'),(55,'gellis','gellis'),(56,'eharrison','eharrison'),(57,'sgibson','sgibson'),(58,'rmcdonald','rmcdonald'),(59,'ccruz','ccruz'),(60,'imarshall','imarshall'),(61,'dortiz','dortiz'),(62,'fgomez','fgomez'),(63,'pmurray','pmurray'),(64,'ffreeman','ffreeman'),(65,'awells','awells'),(66,'cwebb','cwebb'),(67,'ssimpson','ssimpson'),(68,'mstevens','mstevens'),(69,'etucker','etucker'),(70,'eporter','eporter'),(71,'mhunter','mhunter'),(72,'shicks','shicks'),(74,'ehenry','ehenry'),(75,'iboyd','iboyd'),(76,'lmason','lmason'),(77,'rmorales','rmorales'),(78,'mkennedy','mkennedy'),(79,'ewarren','ewarren'),(80,'edixon','edixon');
/*!40000 ALTER TABLE `saltzaile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-17  8:19:50
