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
-- Table structure for table `biblioteca_lista_reproduccion`
--

DROP TABLE IF EXISTS `biblioteca_lista_reproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biblioteca_lista_reproduccion` (
  `idBibliotecaLista` int NOT NULL AUTO_INCREMENT,
  `idListaReproduccionUsuario` int NOT NULL,
  `idCancionBibliotecaLista` int NOT NULL,
  PRIMARY KEY (`idBibliotecaLista`),
  KEY `idListaReproduccionUsuario_idx` (`idListaReproduccionUsuario`),
  KEY `idCancionBibliotecaLista_idx` (`idCancionBibliotecaLista`),
  CONSTRAINT `idCancionBibliotecaLista` FOREIGN KEY (`idCancionBibliotecaLista`) REFERENCES `cancion` (`idCancion`),
  CONSTRAINT `idListaReproduccionUsuario` FOREIGN KEY (`idListaReproduccionUsuario`) REFERENCES `lista_reproduccion_usuario` (`idListaUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biblioteca_lista_reproduccion`
--

LOCK TABLES `biblioteca_lista_reproduccion` WRITE;
/*!40000 ALTER TABLE `biblioteca_lista_reproduccion` DISABLE KEYS */;
INSERT INTO `biblioteca_lista_reproduccion` VALUES (1,3,14),(2,3,13);
/*!40000 ALTER TABLE `biblioteca_lista_reproduccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-20 23:43:51
