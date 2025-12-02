-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: challange
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alerts` (
  `AlertID` int(11) NOT NULL AUTO_INCREMENT,
  `Alert_type` varchar(100) DEFAULT NULL,
  `bericht` text,
  `SensorID` int(11) NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `SensorID` (`SensorID`),
  CONSTRAINT `alerts_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `sensor` (`sensorid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts`
--

LOCK TABLES `alerts` WRITE;
/*!40000 ALTER TABLE `alerts` DISABLE KEYS */;
INSERT INTO `alerts` VALUES (1,'Waarschuwing','Er is langdurig waterverbruik gedetecteerd in de keuken.',1),(2,'Info','De douche is langer dan gemiddeld gebruikt.',2),(3,'Alarm','Ongebruikelijk hoog waterverbruik in de badkamer.',3),(4,'Waarschuwing','Mogelijke lekkage gedetecteerd in de WC.',4),(5,'Info','Waterverbruik in de Keuken is hoger dan vorige week.',5),(6,'Waarschuwing','Sensor meldt verhoogd waterverbruik in de Badkamer.',6),(7,'Alarm','Plotseling piekverbruik gedetecteerd in de keuken.',7),(8,'Info','Normaal verbruik geregistreerd in de badkamer.',8),(9,'Waarschuwing','Douche is langer dan aanbevolen in gebruik.',9),(10,'Alarm','Sterke aanwijzing voor lekkage in de toiletvoorziening.',10);
/*!40000 ALTER TABLE `alerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumptie_data`
--

DROP TABLE IF EXISTS `consumptie_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consumptie_data` (
  `DataID` int(11) NOT NULL AUTO_INCREMENT,
  `Water_verbruik` decimal(10,2) DEFAULT NULL,
  `Flow_state` varchar(50) DEFAULT NULL,
  `SensorID` int(11) NOT NULL,
  PRIMARY KEY (`DataID`),
  KEY `SensorID` (`SensorID`),
  CONSTRAINT `consumptie_data_ibfk_1` FOREIGN KEY (`SensorID`) REFERENCES `sensor` (`sensorid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumptie_data`
--

LOCK TABLES `consumptie_data` WRITE;
/*!40000 ALTER TABLE `consumptie_data` DISABLE KEYS */;
INSERT INTO `consumptie_data` VALUES (25,0.00,'0',1),(26,2.50,'1',1),(27,5.80,'1',1),(28,12.40,'1',1),(29,25.10,'1',1),(30,30.50,'1',1),(31,28.20,'1',1),(32,15.00,'1',1),(33,4.20,'1',1),(34,0.00,'0',1),(35,0.00,'0',1),(36,8.50,'1',1),(37,18.90,'1',1),(38,35.40,'1',1),(39,12.20,'1',1),(40,0.00,'0',1),(41,5.00,'1',2),(42,5.50,'1',2),(43,5.20,'1',2),(44,5.80,'1',2),(45,5.10,'1',2),(46,0.00,'0',3),(47,26.02,'1',3),(48,10.14,'1',3),(49,34.43,'1',3),(50,48.26,'1',3),(51,15.50,'1',3),(52,0.00,'0',3),(53,18.17,'1',3),(54,29.88,'1',3),(55,0.00,'0',3),(56,0.00,'0',4),(57,0.00,'0',4),(58,0.00,'0',4),(59,23.51,'1',4),(60,0.00,'0',4),(61,0.00,'0',4),(62,0.00,'0',4),(63,17.76,'1',4),(64,28.51,'1',4),(65,0.00,'0',4),(66,47.40,'1',5),(67,27.86,'1',5),(68,0.00,'0',5),(69,36.04,'1',5),(70,0.00,'0',5),(71,33.52,'1',5),(72,0.00,'0',5),(73,2.12,'1',5),(74,0.00,'0',5),(75,23.38,'1',5),(76,35.99,'1',6),(77,0.00,'0',6),(78,0.00,'0',6),(79,12.55,'1',6),(80,11.18,'1',6),(81,33.07,'1',6),(82,0.00,'0',6),(83,5.01,'1',6),(84,33.56,'1',6),(85,0.00,'0',6),(86,22.17,'1',7),(87,0.00,'0',7),(88,16.12,'1',7),(89,0.00,'0',7),(90,0.00,'0',7),(91,20.89,'1',7),(92,10.26,'1',7),(93,1.03,'1',7),(94,28.96,'1',7),(95,0.00,'0',7),(96,42.03,'1',8),(97,17.01,'1',8),(98,0.00,'0',8),(99,2.59,'1',8),(100,0.00,'0',8),(101,8.94,'1',8),(102,22.89,'1',8),(103,0.00,'0',8),(104,0.00,'0',8),(105,0.00,'0',8),(106,10.61,'1',9),(107,0.00,'0',9),(108,20.91,'1',9),(109,0.00,'0',9),(110,0.00,'0',9),(111,0.00,'0',9),(112,22.34,'1',9),(113,0.00,'0',9),(114,0.00,'0',9),(115,0.00,'0',9),(116,0.00,'0',10),(117,30.10,'1',10),(118,0.00,'0',10),(119,0.00,'0',10),(120,0.00,'0',10),(121,49.64,'1',10),(122,0.00,'0',10),(123,33.71,'1',10),(124,0.00,'0',10),(125,0.00,'0',10);
/*!40000 ALTER TABLE `consumptie_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gebruiker`
--

DROP TABLE IF EXISTS `gebruiker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gebruiker` (
  `GebruikerID` int(11) NOT NULL AUTO_INCREMENT,
  `Naam` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Wachtwoord` varchar(255) NOT NULL,
  PRIMARY KEY (`GebruikerID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gebruiker`
--

LOCK TABLES `gebruiker` WRITE;
/*!40000 ALTER TABLE `gebruiker` DISABLE KEYS */;
INSERT INTO `gebruiker` VALUES (1,'Emma Jansen','emma.jansen@example.com','Wachtwoord123'),(2,'Ahad','liam.devries@example.com','Water2025'),(3,'Sofia Bakker','sofia.bakker@example.com','FlowSecure!'),(4,'Noah Visser','noah.visser@example.com','Sensor@123'),(5,'Mila Smit','mila.smit@example.com','AquaPass1'),(6,'Daan Mulder','daan.mulder@example.com','LeakAlert!'),(7,'Tess Willems','tess.willems@example.com','HydroSafe99'),(8,'Finn Vos','finn.vos@example.com','WaterFlow!'),(9,'Lotte Meijer','lotte.meijer@example.com','AquaSecure22'),(10,'Sam de Boer','sam.deboer@example.com','H2Osystem!'),(11,'Alex','jood@email.com','jood123');
/*!40000 ALTER TABLE `gebruiker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor` (
  `SensorID` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(50) DEFAULT NULL,
  `Locatie` varchar(255) DEFAULT NULL,
  `GebruikerID` int(11) NOT NULL,
  PRIMARY KEY (`SensorID`),
  KEY `GebruikerID` (`GebruikerID`),
  CONSTRAINT `sensor_ibfk_1` FOREIGN KEY (`GebruikerID`) REFERENCES `gebruiker` (`gebruikerid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
INSERT INTO `sensor` VALUES (1,'Actief','Keuken',2),(2,'Niet Actief','Douche',2),(3,'Actief','Keuken',1),(4,'Niet Actief','Douche',2),(5,'Actief','WC',3),(6,'Actief','Douche',4),(7,'Niet Actief','Badkamer',5),(8,'Actief','Douche',6),(9,'Niet Actief','Badkamer',7),(10,'Actief','Douche',8),(11,'Actief','Keuken',9),(12,'Niet Actief','WC',10);
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-02 10:38:50
