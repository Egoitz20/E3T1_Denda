CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bista_langilesaltzaile` AS
    SELECT 
        `l1`.`ID` AS `ID`,
        CONCAT(`l1`.`IZENA`, ' ', `l1`.`ABIZENA`) AS `LANGILE_IZENA_ABIZENAK`,
        `l1`.`EMAILA` AS `EMAILA`,
        `l1`.`TELEFONOA` AS `TELEFONOA`,
        `l1`.`KONTRATAZIO_DATA` AS `KONTRATAZIO_DATA`,
        `l1`.`ID_NAGUSI` AS `ID_NAGUSI`,
        CONCAT(`l2`.`IZENA`, ' ', `l2`.`ABIZENA`) AS `NAGUSI_IZENA_ABIZENAK`,
        `l1`.`SOLDATA` AS `SOLDATA`,
        `saltzaile`.`ERABILTZAILEA` AS `ERABILTZAILEA`,
        `saltzaile`.`PASAHITZA` AS `PASAHITZA`
    FROM
        ((`langile` `l1`
        JOIN `saltzaile` ON ((`l1`.`ID` = `saltzaile`.`ID`)))
        LEFT JOIN `langile` `l2` ON ((`l1`.`ID_NAGUSI` = `l2`.`ID`)))