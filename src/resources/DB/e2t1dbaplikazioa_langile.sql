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
-- Table structure for table `langile`
--

DROP TABLE IF EXISTS `langile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langile` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IZENA` varchar(50) NOT NULL,
  `ABIZENA` varchar(50) NOT NULL,
  `EMAILA` varchar(50) NOT NULL,
  `TELEFONOA` varchar(50) DEFAULT NULL,
  `KONTRATAZIO_DATA` date NOT NULL,
  `ID_NAGUSI` int DEFAULT NULL,
  `SOLDATA` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKID_NAGUSI` (`ID_NAGUSI`),
  CONSTRAINT `FKID_NAGUSI` FOREIGN KEY (`ID_NAGUSI`) REFERENCES `langile` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langile`
--

LOCK TABLES `langile` WRITE;
/*!40000 ALTER TABLE `langile` DISABLE KEYS */;
INSERT INTO `langile` VALUES (1,'Tommy','Bailey','tommy.bailey@example.com','515.123.4567','2016-06-17',NULL,30000),(2,'Jude','Rivera','jude.rivera@example.com','515.123.4568','2016-09-21',1,31000),(3,'Blake','Cooper','blake.cooper@example.com','515.123.4569','2016-01-13',1,32000),(4,'Louie','Richardson','louie.richardson@example.com','590.423.4567','2016-01-03',3,33000),(5,'Nathan','Cox','nathan.cox@example.com','590.423.4568','2016-05-21',4,34000),(6,'Gabriel','Howard','gabriel.howard@example.com','590.423.4569','2016-06-25',4,35000),(7,'Charles','Ward','charles.ward@example.com','590.423.4560','2016-02-05',4,36000),(8,'Bobby','Torres','bobby.torres@example.com','590.423.5567','2016-02-07',4,37000),(9,'Mohammad','Peterson','mohammad.peterson@example.com','515.124.4569','2016-08-17',2,38000),(10,'Ryan','Gray','ryan.gray@example.com','515.124.4169','2016-08-16',9,39000),(11,'Tyler','Ramirez','tyler.ramirez@example.com','515.124.4269','2016-09-28',9,40000),(12,'Elliott','James','elliott.james@example.com','515.124.4369','2016-09-30',9,41000),(13,'Albert','Watson','albert.watson@example.com','515.124.4469','2016-03-07',9,42000),(14,'Elliot','Brooks','elliTIENDA.brooks@example.com','515.124.4567','2016-12-07',9,43000),(15,'Rory','Kelly','rory.kelly@example.com','515.127.4561','2016-12-07',1,44000),(16,'Alex','Sanders','alex.sanders@example.com','515.127.4562','2016-05-18',47,45000),(17,'Frederick','Price','frederick.price@example.com','515.127.4563','2016-12-24',47,46000),(18,'Ollie','Bennett','ollie.bennett@example.com','515.127.4564','2016-07-24',47,47000),(19,'Louis','Wood','louis.wood@example.com','515.127.4565','2016-11-15',47,48000),(20,'Dexter','Barnes','dexter.barnes@example.com','515.127.4566','2016-08-10',47,NULL),(21,'Jaxon','Ross','jaxon.ross@example.com','650.123.1234','2016-07-18',1,NULL),(22,'Liam','Henderson','liam.henderson@example.com','650.123.2234','2016-04-10',1,NULL),(23,'Jackson','Coleman','jackson.coleman@example.com','650.123.3234','2016-05-01',1,NULL),(24,'Callum','Jenkins','callum.jenkins@example.com','650.123.4234','2016-10-10',1,NULL),(25,'Ronnie','Perry','ronnie.perry@example.com','650.123.5234','2016-11-16',1,NULL),(26,'Leon','Powell','leon.powell@example.com','650.124.1214','2016-07-16',21,NULL),(27,'Kai','Long','kai.long@example.com','650.124.1224','2016-09-28',21,NULL),(28,'Aaron','Patterson','aaron.patterson@example.com','650.124.1334','2016-01-14',21,NULL),(29,'Roman','Hughes','roman.hughes@example.com','650.124.1434','2016-03-08',21,NULL),(30,'Austin','Flores','austin.flores@example.com','650.124.5234','2016-08-20',22,NULL),(31,'Ellis','Washington','ellis.washington@example.com','650.124.6234','2016-10-30',22,NULL),(32,'Jamie','Butler','jamie.butler@example.com','650.124.7234','2016-02-16',22,NULL),(33,'Reggie','Simmons','reggie.simmons@example.com','650.124.8234','2016-04-10',22,NULL),(34,'Seth','Foster','seth.foster@example.com','650.127.1934','2016-06-14',23,NULL),(35,'Carter','Gonzales','carter.gonzales@example.com','650.127.1834','2016-08-26',23,NULL),(36,'Felix','Bryant','felix.bryant@example.com','650.127.1734','2016-12-12',23,NULL),(37,'Ibrahim','Alexander','ibrahim.alexander@example.com','650.127.1634','2016-02-06',23,NULL),(38,'Sonny','Russell','sonny.russell@example.com','650.121.1234','2016-07-14',24,NULL),(39,'Kian','Griffin','kian.griffin@example.com','650.121.2034','2016-10-26',24,NULL),(40,'Caleb','Diaz','caleb.diaz@example.com','650.121.2019','2016-02-12',24,NULL),(41,'Connor','Hayes','connor.hayes@example.com','650.121.1834','2016-04-06',24,NULL),(42,'Amelia','Myers','amelia.myers@example.com','650.121.8009','2016-10-17',25,NULL),(43,'Olivia','Ford','olivia.ford@example.com','650.121.2994','2016-01-29',25,NULL),(44,'Emily','Hamilton','emily.hamilton@example.com','650.121.2874','2016-03-15',25,NULL),(45,'Isla','Graham','isla.graham@example.com','650.121.2004','2016-07-09',25,NULL),(46,'Ava','Sullivan','ava.sullivan@example.com','011.44.1344.429268','2016-10-01',1,NULL),(47,'Ella','Wallace','ella.wallace@example.com','011.44.1344.467268','2016-01-05',1,NULL),(48,'Jessica','Woods','jessica.woods@example.com','011.44.1344.429278','2016-03-10',1,NULL),(49,'Isabella','Cole','isabella.cole@example.com','011.44.1344.619268','2016-10-15',1,NULL),(50,'Mia','West','mia.west@example.com','011.44.1344.429018','2016-01-29',1,NULL),(51,'Poppy','Jordan','poppy.jordan@example.com','011.44.1344.129268','2016-01-30',15,30000),(52,'Sophie','Owens','sophie.owens@example.com','011.44.1344.345268','2016-03-24',15,30000),(53,'Sophia','Reynolds','sophia.reynolds@example.com','011.44.1344.478968','2016-08-20',15,30000),(54,'Lily','Fisher','lily.fisher@example.com','011.44.1344.498718','2016-03-30',15,30000),(55,'Grace','Ellis','grace.ellis@example.com','011.44.1344.987668','2016-12-09',15,30000),(56,'Evie','Harrison','evie.harrison@example.com','011.44.1344.486508','2016-11-23',15,30000),(57,'Scarlett','Gibson','scarlett.gibson@example.com','011.44.1345.429268','2016-01-30',15,30000),(58,'Ruby','Mcdonald','ruby.mcdonald@example.com','011.44.1345.929268','2016-03-04',15,30000),(59,'Chloe','Cruz','chloe.cruz@example.com','011.44.1345.829268','2010-08-01',15,30000),(60,'Isabelle','Marshall','isabelle.marshall@example.com','011.44.1345.729268','2016-03-10',15,30000),(61,'Daisy','Ortiz','daisy.ortiz@example.com','011.44.1345.629268','2016-12-15',15,30000),(62,'Freya','Gomez','freya.gomez@example.com','011.44.1345.529268','2016-11-03',15,30000),(63,'Phoebe','Murray','phoebe.murray@example.com','011.44.1346.129268','2016-11-11',15,30000),(64,'Florence','Freeman','florence.freeman@example.com','011.44.1346.229268','2016-03-19',15,30000),(65,'Alice','Wells','alice.wells@example.com','011.44.1346.329268','2016-01-24',15,30000),(66,'Charlotte','Webb','charlotte.webb@example.com','011.44.1346.529268','2016-02-23',15,30000),(67,'Sienna','Simpson','sienna.simpson@example.com','011.44.1346.629268','2016-03-24',15,30000),(68,'Matilda','Stevens','matilda.stevens@example.com','011.44.1346.729268','2016-04-21',15,30000),(69,'Evelyn','Tucker','evelyn.tucker@example.com','011.44.1343.929268','2016-03-11',15,30000),(70,'Eva','Porter','eva.porter@example.com','011.44.1343.829268','2016-03-23',15,30000),(71,'Millie','Hunter','millie.hunter@example.com','011.44.1343.729268','2016-01-24',15,30000),(72,'Sofia','Hicks','sofia.hicks@example.com','011.44.1343.629268','2016-02-23',15,30000),(74,'Elsie','Henry','elsie.henry@example.com','011.44.1343.329268','2016-04-21',15,30000),(75,'Imogen','Boyd','imogen.boyd@example.com','011.44.1644.429267','2016-05-11',15,30000),(76,'Layla','Mason','layla.mason@example.com','011.44.1644.429266','2016-03-19',15,30000),(77,'Rosie','Morales','rosie.morales@example.com','011.44.1644.429265','2016-03-24',15,30000),(78,'Maya','Kennedy','maya.kennedy@example.com','011.44.1644.429264','2016-04-23',15,30000),(79,'Esme','Warren','esme.warren@example.com','011.44.1644.429263','2016-05-24',15,30000),(80,'Elizabeth','Dixon','elizabeth.dixon@example.com','011.44.1644.429262','2016-01-04',15,30000),(81,'Lola','Ramos','lola.ramos@example.com','650.507.9876','2016-01-24',21,NULL),(82,'Willow','Reyes','willow.reyes@example.com','650.507.9877','2016-02-23',21,NULL),(83,'Ivy','Burns','ivy.burns@example.com','650.507.9878','2016-06-21',21,NULL),(84,'Erin','Gordon','erin.gordon@example.com','650.507.9879','2016-02-03',21,NULL),(85,'Holly','Shaw','holly.shaw@example.com','650.509.1876','2016-01-27',22,NULL),(86,'Emilia','Holmes','emilia.holmes@example.com','650.509.2876','2016-02-20',22,NULL),(87,'Molly','Rice','molly.rice@example.com','650.509.3876','2016-06-24',22,NULL),(88,'Ellie','Robertson','ellie.robertson@example.com','650.509.4876','2016-02-07',22,NULL),(89,'Jasmine','Hunt','jasmine.hunt@example.com','650.505.1876','2016-06-14',23,NULL),(90,'Eliza','Black','eliza.black@example.com','650.505.2876','2016-08-13',23,NULL),(91,'Lilly','Daniels','lilly.daniels@example.com','650.505.3876','2016-07-11',23,NULL),(92,'Abigail','Palmer','abigail.palmer@example.com','650.505.4876','2016-12-19',23,NULL),(93,'Georgia','Mills','georgia.mills@example.com','650.501.1876','2016-02-04',24,NULL),(94,'Maisie','Nichols','maisie.nichols@example.com','650.501.2876','2016-03-03',24,NULL),(95,'Eleanor','Grant','eleanor.grant@example.com','650.501.3876','2016-07-01',24,NULL),(96,'Hannah','Knight','hannah.knight@example.com','650.501.4876','2016-03-17',24,NULL),(97,'Harriet','Ferguson','harriet.ferguson@example.com','650.507.9811','2016-04-24',47,NULL),(98,'Amber','Rose','amber.rose@example.com','650.507.9822','2016-05-23',25,NULL),(99,'Bella','Stone','bella.stone@example.com','650.507.9833','2016-06-21',25,NULL),(100,'Thea','Hawkins','thea.hawkins@example.com','650.507.9844','2016-01-13',25,NULL),(101,'Annabelle','Dunn','annabelle.dunn@example.com','515.123.4444','2016-09-17',2,NULL),(102,'Emma','Perkins','emma.perkins@example.com','515.123.5555','2016-02-17',1,NULL),(103,'Amelie','Hudson','amelie.hudson@example.com','603.123.6666','2016-08-17',102,NULL),(104,'Harper','Spencer','harper.spencer@example.com','515.123.7777','2016-06-07',2,NULL),(105,'Gracie','Gardner','gracie.gardner@example.com','515.123.8888','2016-06-07',2,NULL),(106,'Rose','Stephens','rose.stephens@example.com','515.123.8080','2016-06-07',2,NULL),(107,'Summer','Payne','summer.payne@example.com','515.123.8181','2016-06-07',106,NULL);
/*!40000 ALTER TABLE `langile` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SALTZAILE_ERREGISTRO_KONTROLA` AFTER INSERT ON `langile` FOR EACH ROW BEGIN
	INSERT INTO TXERTAKETAK_ETA_EZABAKETAK_KONTROLA (TXERTAKETAK) VALUES (CONCAT('SALTZAILE BERRIA: ', NEW.IZENA, ' ',NEW.ABIZENA, ' - TXERTATU EGIN DA ', CURDATE(), ' DATAREKIN'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SALTZAILE_DATU_ALDAKETAK` AFTER UPDATE ON `langile` FOR EACH ROW BEGIN
		INSERT INTO EGUNERAKETAK_KONTROLA(KONTROLA) 
        VALUES (CONCAT('Saltzaile: ', OLD.IZENA, ' ', OLD.ABIZENA,' ', OLD.EMAILA,' ', OLD.TELEFONOA, ' ', OLD.SOLDATA,' ', OLD.KONTRATAZIO_DATA,' ', OLD.ID_NAGUSI, ' -rekin aldatu du informazioa honetara: ', 
		NEW.IZENA, ' ', NEW.ABIZENA,' ', NEW.EMAILA,' ', NEW.TELEFONOA, ' ', NEW.SOLDATA,' ', NEW.KONTRATAZIO_DATA,' ', NEW.ID_NAGUSI));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SALTZAILE_EZABAKETAK_KONTROLA` AFTER DELETE ON `langile` FOR EACH ROW BEGIN
	INSERT INTO TXERTAKETAK_ETA_EZABAKETAK_KONTROLA (EZABAKETAK) VALUES (CONCAT('SALTZAILE EZABAKETA: ', OLD.IZENA, ' ', OLD.ABIZENA,' IZEN ABIZENAREKIN EZABATU EGIN DA ', CURDATE(), ' DATAREKIN'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-17 12:59:33
