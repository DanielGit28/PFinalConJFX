CREATE DATABASE  IF NOT EXISTS `bdbaulrecuerdos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bdbaulrecuerdos`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdbaulrecuerdos
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `biblioteca_album`
--

DROP TABLE IF EXISTS `biblioteca_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biblioteca_album` (
  `idBibliotecaAlbum` int NOT NULL AUTO_INCREMENT,
  `idAlbumBiblioteca` int NOT NULL,
  `idCancionAlbumBiblioteca` int NOT NULL,
  PRIMARY KEY (`idBibliotecaAlbum`),
  KEY `idCancion_idx` (`idCancionAlbumBiblioteca`),
  KEY `idCancionAlbum` (`idCancionAlbumBiblioteca`),
  KEY `idAlbumCanciones_idx` (`idAlbumBiblioteca`),
  CONSTRAINT `idAlbumBiblioteca` FOREIGN KEY (`idAlbumBiblioteca`) REFERENCES `album` (`idAlbum`),
  CONSTRAINT `idCancionAlbumBiblioteca` FOREIGN KEY (`idCancionAlbumBiblioteca`) REFERENCES `cancion` (`idCancion`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biblioteca_album`
--

LOCK TABLES `biblioteca_album` WRITE;
/*!40000 ALTER TABLE `biblioteca_album` DISABLE KEYS */;
INSERT INTO `biblioteca_album` VALUES (12,1,4),(13,1,4),(14,1,4),(15,1,4),(16,1,4),(17,1,4),(18,1,4),(19,1,4),(20,1,4),(21,1,4),(22,1,4),(23,1,4),(24,1,4),(25,1,4),(26,1,4),(27,1,4),(28,1,4),(29,1,4),(30,2,6),(31,1,4),(32,2,7),(33,2,8),(34,2,9),(35,2,10),(36,2,11),(37,2,12),(38,1,13),(39,1,4),(40,1,4),(41,1,4),(42,1,4),(43,1,4),(44,1,4),(45,1,4),(46,1,4),(47,1,4),(48,1,4),(49,1,4),(50,1,4),(51,1,4),(52,1,4),(53,1,4),(54,1,4),(55,1,4),(56,1,4),(57,1,4),(58,1,4),(59,1,4),(60,1,4),(61,1,4),(62,1,4),(63,1,4),(64,1,4),(65,1,4),(66,1,4),(67,1,4),(68,1,4),(69,1,4),(70,1,4),(71,1,4),(72,1,4),(73,1,4),(74,1,4),(75,1,4),(76,1,4),(77,1,4),(78,1,4),(79,1,4),(80,1,4),(81,1,4),(82,1,4),(83,1,4),(84,1,4),(85,1,4),(86,1,4),(87,1,4),(88,1,4),(89,1,4),(90,1,4),(91,1,4),(92,1,4),(93,1,4),(94,1,4),(95,1,4),(96,1,4),(97,1,4),(98,1,4),(99,1,4),(100,1,4),(101,1,4),(102,1,4),(103,1,4),(104,1,4),(105,1,4),(106,1,4),(107,1,4),(108,1,4),(109,1,4),(110,1,4),(111,1,4),(112,1,4),(113,1,4),(114,1,4),(115,1,4),(116,1,4),(117,1,4),(118,1,4),(119,1,4),(120,1,4),(121,1,4),(122,1,4),(123,1,4),(124,1,4),(125,1,4),(126,1,4),(127,1,4),(128,1,4),(129,1,4),(130,1,4),(131,1,4),(132,1,4),(133,1,4),(134,1,4),(135,1,4),(136,1,4),(137,1,4),(138,1,4),(139,1,4),(140,1,4),(141,1,4),(142,1,4),(143,1,4),(144,1,4),(145,1,4),(146,1,4),(147,1,4),(148,1,4),(149,1,4),(150,1,4),(151,1,4),(152,1,4),(153,1,4),(154,1,4),(155,1,4);
/*!40000 ALTER TABLE `biblioteca_album` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-20 23:43:50
