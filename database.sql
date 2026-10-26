-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: routefinder_db
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `is_bus_stop` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'abuja building',0),(2,'St.peters catholic church',0),(3,'Hilltop junction',1),(6,'Faculty of education',1),(7,'Pharmacy',1),(8,'Freedom square',1),(9,'Agric',0),(10,'Cedr Hall',1),(11,'Odim gate',0),(12,'ogige market Shuttle park',1),(13,'Ziks flat',1),(14,'Enijoku boys hostel',0),(15,'Main gate',1),(16,'Second gate',1),(17,'Eyoyita hostel',1),(18,'Marlima',0),(19,'Biological sciences',0),(20,'faculty of Arts',0),(21,'SUB',1),(22,'Okpara hostel',1),(23,'GS building',0),(24,'Vet',1),(25,'Greenhouse',1),(26,'Garage',0),(27,'UBA hall of fame',1),(28,'Total',1),(29,'UNN Guest House',0),(30,'Admission office',0),(31,'Container',0),(32,'FPSLT',0),(33,'Christ Church',0),(34,'JIMBAZZ',0),(35,'Medical center',1),(36,'Bookshop',0),(37,'VC office',0),(38,'Nutrition and dietics',0),(39,'ICT',1),(40,'UNN stadium',0),(41,'First Bank',0),(42,'Chitis',0);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nearest_bus_stops`
--

DROP TABLE IF EXISTS `nearest_bus_stops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nearest_bus_stops` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_id` int NOT NULL,
  `bus_stop_id` int NOT NULL,
  `walking_distance` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_location` (`location_id`),
  KEY `fk_busstop` (`bus_stop_id`),
  CONSTRAINT `fk_busstop` FOREIGN KEY (`bus_stop_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_location` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nearest_bus_stops_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `nearest_bus_stops_ibfk_2` FOREIGN KEY (`bus_stop_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nearest_bus_stops`
--

LOCK TABLES `nearest_bus_stops` WRITE;
/*!40000 ALTER TABLE `nearest_bus_stops` DISABLE KEYS */;
INSERT INTO `nearest_bus_stops` VALUES (1,29,7,2),(2,30,7,3),(3,31,39,2),(4,23,6,4),(5,33,15,5),(6,36,7,3),(7,37,20,4),(8,38,7,2),(9,40,16,7),(10,41,7,3),(11,42,7,3);
/*!40000 ALTER TABLE `nearest_bus_stops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'victor','2025-11-14 18:53:05'),(2,'unn guest house','2025-11-14 19:03:10'),(3,'favour','2025-12-27 14:07:02'),(4,'Joy','2026-07-23 14:32:41'),(5,'Collins','2026-10-21 14:55:08');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-26 17:15:55
