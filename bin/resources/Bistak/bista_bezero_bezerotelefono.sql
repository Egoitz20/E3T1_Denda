CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bista_bezero_bezerotelefono` AS
    SELECT 
        `bezero`.`ID` AS `ID`,
        `bezero`.`IZENA` AS `IZENA`,
        `bezero`.`ABIZENA` AS `ABIZENA`,
        `bezero`.`NAN` AS `NAN`,
        `bezero`.`EMAILA` AS `EMAILA`,
        `bezero`.`HELBIDEA` AS `HELBIDEA`,
        `bezero_telefono`.`ZENBAKIA` AS `ZENBAKIA`,
        `bezero`.`ERABILTZAILEA` AS `ERABILTZAILEA`,
        `bezero`.`PASAHITZA` AS `PASAHITZA`
    FROM
        (`bezero`
        JOIN `bezero_telefono` ON ((`bezero`.`ID` = `bezero_telefono`.`ID_BEZERO`)))