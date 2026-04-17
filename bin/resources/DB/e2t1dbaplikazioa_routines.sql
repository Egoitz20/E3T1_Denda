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
-- Temporary view structure for view `bista_bezero_eskaerak`
--

DROP TABLE IF EXISTS `bista_bezero_eskaerak`;
/*!50001 DROP VIEW IF EXISTS `bista_bezero_eskaerak`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_bezero_eskaerak` AS SELECT 
 1 AS `PRODUKTU`,
 1 AS `KATEGORIA`,
 1 AS `KOPURUA`,
 1 AS `DATA`,
 1 AS `DESKRIBAPEN`,
 1 AS `SALTZAILEA`,
 1 AS `IZENA`,
 1 AS `ABIZENA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bista_langilesaltzaile`
--

DROP TABLE IF EXISTS `bista_langilesaltzaile`;
/*!50001 DROP VIEW IF EXISTS `bista_langilesaltzaile`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_langilesaltzaile` AS SELECT 
 1 AS `ID`,
 1 AS `LANGILE_IZENA_ABIZENAK`,
 1 AS `EMAILA`,
 1 AS `TELEFONOA`,
 1 AS `KONTRATAZIO_DATA`,
 1 AS `ID_NAGUSI`,
 1 AS `NAGUSI_IZENA_ABIZENAK`,
 1 AS `SOLDATA`,
 1 AS `ERABILTZAILEA`,
 1 AS `PASAHITZA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bista_eguneraketak`
--

DROP TABLE IF EXISTS `bista_eguneraketak`;
/*!50001 DROP VIEW IF EXISTS `bista_eguneraketak`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_eguneraketak` AS SELECT 
 1 AS `KONTROLA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bista_txertaketak_ezabaketak`
--

DROP TABLE IF EXISTS `bista_txertaketak_ezabaketak`;
/*!50001 DROP VIEW IF EXISTS `bista_txertaketak_ezabaketak`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_txertaketak_ezabaketak` AS SELECT 
 1 AS `TXERTAKETAK`,
 1 AS `EZABAKETAK`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bista_bezero_bezerotelefono`
--

DROP TABLE IF EXISTS `bista_bezero_bezerotelefono`;
/*!50001 DROP VIEW IF EXISTS `bista_bezero_bezerotelefono`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_bezero_bezerotelefono` AS SELECT 
 1 AS `ID`,
 1 AS `IZENA`,
 1 AS `ABIZENA`,
 1 AS `NAN`,
 1 AS `EMAILA`,
 1 AS `HELBIDEA`,
 1 AS `ZENBAKIA`,
 1 AS `ERABILTZAILEA`,
 1 AS `PASAHITZA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bista_produktu_kategoria`
--

DROP TABLE IF EXISTS `bista_produktu_kategoria`;
/*!50001 DROP VIEW IF EXISTS `bista_produktu_kategoria`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bista_produktu_kategoria` AS SELECT 
 1 AS `ID`,
 1 AS `PRODUKTU_IZENA`,
 1 AS `DESKRIBAPENA`,
 1 AS `SALNEURRIA`,
 1 AS `KATEGORIA_IZENA`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `bista_bezero_eskaerak`
--

/*!50001 DROP VIEW IF EXISTS `bista_bezero_eskaerak`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_bezero_eskaerak` AS select `produktu`.`IZENA` AS `PRODUKTU`,`kategoria`.`IZENA` AS `KATEGORIA`,`eskari_lerro`.`KOPURUA` AS `KOPURUA`,`eskari`.`ESKAERA_DATA` AS `DATA`,`eskari_egoera`.`DESKRIBAPENA` AS `DESKRIBAPEN`,concat(`langile`.`IZENA`,' ',`langile`.`ABIZENA`) AS `SALTZAILEA`,`bezero`.`IZENA` AS `IZENA`,`bezero`.`ABIZENA` AS `ABIZENA` from (((((((`produktu` join `kategoria` on((`produktu`.`ID_KATEGORIA` = `kategoria`.`ID`))) join `eskari_lerro` on((`produktu`.`ID` = `eskari_lerro`.`ID_PRODUKTU`))) join `eskari` on((`eskari_lerro`.`ID_ESKARI` = `eskari`.`ID`))) join `eskari_egoera` on((`eskari`.`ID_EGOERA` = `eskari_egoera`.`ID`))) join `saltzaile` on((`eskari`.`ID_SALTZAILE` = `saltzaile`.`ID`))) join `langile` on((`saltzaile`.`ID` = `langile`.`ID`))) join `bezero` on((`eskari`.`ID_BEZERO` = `bezero`.`ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bista_langilesaltzaile`
--

/*!50001 DROP VIEW IF EXISTS `bista_langilesaltzaile`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_langilesaltzaile` AS select `l1`.`ID` AS `ID`,concat(`l1`.`IZENA`,' ',`l1`.`ABIZENA`) AS `LANGILE_IZENA_ABIZENAK`,`l1`.`EMAILA` AS `EMAILA`,`l1`.`TELEFONOA` AS `TELEFONOA`,`l1`.`KONTRATAZIO_DATA` AS `KONTRATAZIO_DATA`,`l1`.`ID_NAGUSI` AS `ID_NAGUSI`,concat(`l2`.`IZENA`,' ',`l2`.`ABIZENA`) AS `NAGUSI_IZENA_ABIZENAK`,`l1`.`SOLDATA` AS `SOLDATA`,`saltzaile`.`ERABILTZAILEA` AS `ERABILTZAILEA`,`saltzaile`.`PASAHITZA` AS `PASAHITZA` from ((`langile` `l1` join `saltzaile` on((`l1`.`ID` = `saltzaile`.`ID`))) left join `langile` `l2` on((`l1`.`ID_NAGUSI` = `l2`.`ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bista_eguneraketak`
--

/*!50001 DROP VIEW IF EXISTS `bista_eguneraketak`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_eguneraketak` AS select `eguneraketak_kontrola`.`KONTROLA` AS `KONTROLA` from `eguneraketak_kontrola` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bista_txertaketak_ezabaketak`
--

/*!50001 DROP VIEW IF EXISTS `bista_txertaketak_ezabaketak`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_txertaketak_ezabaketak` AS select `txertaketak_eta_ezabaketak_kontrola`.`TXERTAKETAK` AS `TXERTAKETAK`,`txertaketak_eta_ezabaketak_kontrola`.`EZABAKETAK` AS `EZABAKETAK` from `txertaketak_eta_ezabaketak_kontrola` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bista_bezero_bezerotelefono`
--

/*!50001 DROP VIEW IF EXISTS `bista_bezero_bezerotelefono`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_bezero_bezerotelefono` AS select `bezero`.`ID` AS `ID`,`bezero`.`IZENA` AS `IZENA`,`bezero`.`ABIZENA` AS `ABIZENA`,`bezero`.`NAN` AS `NAN`,`bezero`.`EMAILA` AS `EMAILA`,`bezero`.`HELBIDEA` AS `HELBIDEA`,`bezero_telefono`.`ZENBAKIA` AS `ZENBAKIA`,`bezero`.`ERABILTZAILEA` AS `ERABILTZAILEA`,`bezero`.`PASAHITZA` AS `PASAHITZA` from (`bezero` left join `bezero_telefono` on((`bezero`.`ID` = `bezero_telefono`.`ID_BEZERO`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bista_produktu_kategoria`
--

/*!50001 DROP VIEW IF EXISTS `bista_produktu_kategoria`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bista_produktu_kategoria` AS select `produktu`.`ID` AS `ID`,`produktu`.`IZENA` AS `PRODUKTU_IZENA`,`produktu`.`DESKRIBAPENA` AS `DESKRIBAPENA`,`produktu`.`SALNEURRIA` AS `SALNEURRIA`,`kategoria`.`IZENA` AS `KATEGORIA_IZENA` from (`produktu` join `kategoria` on((`produktu`.`ID_KATEGORIA` = `kategoria`.`ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'e2t1dbaplikazioa'
--

--
-- Dumping routines for database 'e2t1dbaplikazioa'
--
/*!50003 DROP PROCEDURE IF EXISTS `BEZERO_ALDAKETA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BEZERO_ALDAKETA`(
	IN SARRERA_ID INT,
    IN SARRERA_IZENA VARCHAR(100), 
    IN SARRERA_ABIZENA VARCHAR(100), 
    IN SARRERA_NAN VARCHAR(9), 
    IN SARRERA_EMAILA VARCHAR(100), 
    IN SARRERA_HELBIDEA VARCHAR(100), 
    IN SARRERA_ERABILTZAILEA VARCHAR (100),
    IN SARRERA_PASAHITZA VARCHAR (100), 
    IN SARRERA_ZENBAKIA VARCHAR(100)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    UPDATE BEZERO SET 
        IZENA = SARRERA_IZENA, 
        ABIZENA = SARRERA_ABIZENA, 
        NAN = SARRERA_NAN,
        EMAILA = SARRERA_EMAILA, 
        HELBIDEA = SARRERA_HELBIDEA, 
        ERABILTZAILEA = SARRERA_ERABILTZAILEA, 
        PASAHITZA = SARRERA_PASAHITZA
    WHERE ID = SARRERA_ID;
    
    UPDATE BEZERO_TELEFONO SET
        ZENBAKIA = SARRERA_ZENBAKIA
    WHERE ID_BEZERO = SARRERA_ID;
    
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BEZERO_ESKAERAK_INFORMAZIOA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BEZERO_ESKAERAK_INFORMAZIOA`(IN SARRERAIZENA VARCHAR(50), IN SARRERAABIZENA VARCHAR(100))
BEGIN
	SELECT PRODUKTU,KATEGORIA, KOPURUA, DATA, DESKRIBAPEN, SALTZAILEA 
    FROM BISTA_BEZERO_ESKAERAK 
    WHERE IZENA = SARRERAIZENA AND ABIZENA = SARRERAABIZENA;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BEZERO_EZABATU` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BEZERO_EZABATU`(
    IN SARRERA_ID INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    DELETE FROM BEZERO WHERE ID = SARRERA_ID;
    DELETE FROM BEZERO_TELEFONO WHERE ID_BEZERO = SARRERA_ID;
    
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BEZERO_GEHIKETA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BEZERO_GEHIKETA`(
    IN SARRERA_IZENA VARCHAR(100), 
    IN SARRERA_ABIZENA VARCHAR(100), 
    IN SARRERA_NAN VARCHAR(9), 
    IN SARRERA_EMAILA VARCHAR(100), 
    IN SARRERA_HELBIDEA VARCHAR(100), 
    IN SARRERA_ERABILTZAILEA VARCHAR (100),
    IN SARRERA_PASAHITZA VARCHAR (100), 
    IN SARRERA_ZENBAKIA VARCHAR(100)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;

	INSERT INTO BEZERO (IZENA, ABIZENA, NAN, EMAILA, HELBIDEA, ERABILTZAILEA, PASAHITZA) 
    VALUES (SARRERA_IZENA, SARRERA_ABIZENA, SARRERA_NAN, SARRERA_EMAILA, SARRERA_HELBIDEA, SARRERA_ERABILTZAILEA, SARRERA_PASAHITZA);
    
    INSERT INTO BEZERO_TELEFONO (ID_BEZERO, ZENBAKIA) 
    VALUES (LAST_INSERT_ID(), SARRERA_ZENBAKIA);
    
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ERREGISTROA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ERREGISTROA`(IN SARRERAERABILTZAILEA VARCHAR(100), IN SARRERAPASAHITZA VARCHAR(100))
BEGIN
	INSERT INTO BEZERO (IZENA, ABIZENA, EMAILA, ERABILTZAILEA, PASAHITZA) VALUES ('Izena', 'Abizena', 'Emaila', SARRERAERABILTZAILEA, SARRERAPASAHITZA);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `KATEGORIA_PRODUKTU_INFO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `KATEGORIA_PRODUKTU_INFO`(IN SARRERAKATEGORIAIZENA VARCHAR(100))
BEGIN
	SELECT ID, PRODUKTU_IZENA, DESKRIBAPENA, SALNEURRIA FROM BISTA_PRODUKTU_KATEGORIA WHERE KATEGORIA_IZENA = SARRERAKATEGORIAIZENA;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALTZAILE_ALDAKETA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALTZAILE_ALDAKETA`(
    IN SARRERA_ID INT, 
    IN SARRERA_IZENA VARCHAR(100), 
    IN SARRERA_ABIZENA VARCHAR(100), 
    IN SARRERA_ERABILTZAILEA VARCHAR(100), 
    IN SARRERA_PASAHITZA VARCHAR(100), 
    IN SARRERA_EMAILA VARCHAR(100), 
    IN SARRERA_TELEFONOA VARCHAR(100), 
    IN SARRERA_SOLDATA INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Actualizar LANGILE
    UPDATE LANGILE SET 
        IZENA = SARRERA_IZENA, 
        ABIZENA = SARRERA_ABIZENA, 
        EMAILA = SARRERA_EMAILA, 
        TELEFONOA = SARRERA_TELEFONOA, 
        SOLDATA = SARRERA_SOLDATA 
    WHERE ID = SARRERA_ID;

    -- Actualizar SALTZAILE
    UPDATE SALTZAILE SET
        ERABILTZAILEA = SARRERA_ERABILTZAILEA, 
        PASAHITZA = SARRERA_PASAHITZA 
    WHERE ID = SARRERA_ID;
    
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALTZAILE_EZABATU` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALTZAILE_EZABATU`(
    IN SARRERA_ID INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Eliminar de SALTZAILE primero (por la foreign key)
    DELETE FROM SALTZAILE WHERE ID = SARRERA_ID;
    
    -- Eliminar de LANGILE
    DELETE FROM LANGILE WHERE ID = SARRERA_ID;
    
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALTZAILE_GEHIKETA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALTZAILE_GEHIKETA`(
    IN SARRERA_IZENA VARCHAR(100), 
    IN SARRERA_ABIZENA VARCHAR(100), 
    IN SARRERA_ERABILTZAILEA VARCHAR(100), 
    IN SARRERA_PASAHITZA VARCHAR(100), 
    IN SARRERA_EMAILA VARCHAR(100), 
    IN SARRERA_TELEFONOA VARCHAR(100), 
    IN SARRERA_SOLDATA INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Insertar en LANGILE (el ID se genera automáticamente con AUTO_INCREMENT)
    INSERT INTO LANGILE (IZENA, ABIZENA, EMAILA, TELEFONOA, KONTRATAZIO_DATA, ID_NAGUSI, SOLDATA) 
    VALUES (SARRERA_IZENA, SARRERA_ABIZENA, SARRERA_EMAILA, SARRERA_TELEFONOA, CURDATE(), 15, SARRERA_SOLDATA);
    
    -- Insertar en SALTZAILE con el MISMO ID generado automáticamente
    INSERT INTO SALTZAILE (ID, ERABILTZAILEA, PASAHITZA) 
    VALUES (LAST_INSERT_ID(), SARRERA_ERABILTZAILEA, SARRERA_PASAHITZA);
    
    COMMIT;
    
END ;;
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

-- Dump completed on 2026-04-17 12:59:35
