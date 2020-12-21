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
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancion` (
  `idCancion` int NOT NULL AUTO_INCREMENT,
  `nombreCancion` varchar(60) NOT NULL,
  `idArtistaCancion` int DEFAULT '0',
  `idCompositorCancion` int DEFAULT '0',
  `fechaLanzamiento` date NOT NULL,
  `idGeneroCancion` int NOT NULL,
  `cancionSimple` int NOT NULL,
  `cancionCompra` int NOT NULL,
  `precio` int DEFAULT '0',
  `idAlbumCancion` int DEFAULT '0',
  `recurso` varchar(300) NOT NULL,
  PRIMARY KEY (`idCancion`),
  KEY `idArtista_idx` (`idArtistaCancion`),
  KEY `idCompositor_idx` (`idCompositorCancion`),
  KEY `idGeneroCancion_idx` (`idGeneroCancion`),
  KEY `idAlbum_idx` (`idAlbumCancion`),
  CONSTRAINT `idAlbumCancion` FOREIGN KEY (`idAlbumCancion`) REFERENCES `album` (`idAlbum`),
  CONSTRAINT `idArtistaCancion` FOREIGN KEY (`idArtistaCancion`) REFERENCES `artista` (`idArtista`),
  CONSTRAINT `idCompositorCancion` FOREIGN KEY (`idCompositorCancion`) REFERENCES `compositor` (`idCompositor`),
  CONSTRAINT `idGeneroCancion` FOREIGN KEY (`idGeneroCancion`) REFERENCES `genero` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` VALUES (4,'Default',4,9,'2020-12-16',10,1,1,1,1,'def'),(5,'Dakiti',5,9,'2020-10-22',11,1,1,1000,1,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\Bad Bunny Ft. Jhay Cortez - Dakiti.mp3'),(6,'Mojaita',6,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\01. J Balvin Y Bad Bunny - Mojaita.mp3'),(7,'Yo le llego',5,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\02. J Balvin Y Bad Bunny - Yo Le Llego.mp3'),(8,'Cuidado por ahí',6,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\03. J Balvin Y Bad Bunny - Cuidao Por Ahi.mp3'),(9,'Que pretendes',5,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\04. J Balvin Y Bad Bunny - Que Pretendes.mp3'),(10,'La canción',6,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\05. J Balvin Y Bad Bunny - La Cancion.mp3'),(11,'Un peso',5,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\06. J Balvin Y Bad Bunny Ft. Marciano Cantero - Un Peso.mp3'),(12,'Odio',6,9,'2019-06-29',11,1,1,1000,2,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\07. J Balvin Y Bad Bunny - Odio.mp3'),(13,'Como un bebé',5,9,'2019-06-29',11,1,1,1000,1,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\08. J Balvin Y Bad Bunny Ft. Mr. Eazi - Como Un Bebe.mp3'),(14,'Mala',7,9,'2020-05-15',11,2,1,0,1,'C:\\Users\\Daniel\\Documents\\UCenfotec\\Programacion Orientada a Objetos\\ProyectoFinalPOO\\PFinalCon JFX\\PruebasMusica\\Ozuna - Mala.mp3');
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
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
