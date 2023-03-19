CREATE DATABASE  IF NOT EXISTS `podsis1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `podsis1`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: podsis1
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `grad`
--

DROP TABLE IF EXISTS `grad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `drzava` varchar(255) NOT NULL,
  `postanski_broj` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grad`
--

LOCK TABLES `grad` WRITE;
/*!40000 ALTER TABLE `grad` DISABLE KEYS */;
INSERT INTO `grad` VALUES (1,'Beograd','Srbija','11050'),(2,'Despotovac','Srbija','35213'),(3,'Njujork','USA','111'),(4,'London','Engleska','222'),(5,'Pariz','Francuska','333'),(6,'Barselona','Spanija','123'),(14,'Tokio','Japan','444'),(15,'Solun','Grcka','777'),(18,'Lisabon','Portugal','999');
/*!40000 ALTER TABLE `grad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `korisnickoime` varchar(255) NOT NULL,
  `sifra` varchar(255) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `adresa` varchar(255) NOT NULL,
  `grad_id` int NOT NULL,
  `novac` decimal(20,3) NOT NULL DEFAULT '0.000',
  PRIMARY KEY (`korisnickoime`),
  KEY `grad_korisnik_grad_id_idx` (`grad_id`),
  CONSTRAINT `grad_korisnik_grad_id` FOREIGN KEY (`grad_id`) REFERENCES `grad` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('1','2','Marko','Markovic','Nova ulica BB',1,6.000),('lola','123','Lara','Lazarevic','Avenija',15,60000.000),('mj','1','Mina','Jovanovic','Kajmakcalanska 1',1,5.000),('pera','peric','Pera','Peric','Ulica123',3,33000.000),('rok2001','123','Roko','Rokic','Bulevar 5/12',5,5.000);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-22  4:17:03
CREATE DATABASE  IF NOT EXISTS `podsis3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `podsis3`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: podsis3
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `narudzbina`
--

DROP TABLE IF EXISTS `narudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbina` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ukupna_cena` decimal(20,3) DEFAULT NULL,
  `vreme_kreiranja` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adresa` varchar(255) NOT NULL,
  `grad` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `narudz_grad_idx` (`grad`),
  CONSTRAINT `narudz_grad` FOREIGN KEY (`grad`) REFERENCES `podsis1`.`grad` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbina`
--

LOCK TABLES `narudzbina` WRITE;
/*!40000 ALTER TABLE `narudzbina` DISABLE KEYS */;
INSERT INTO `narudzbina` VALUES (1,141000.000,'2023-02-22 03:06:31','Ulica123',1);
/*!40000 ALTER TABLE `narudzbina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recenzija`
--

DROP TABLE IF EXISTS `recenzija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recenzija` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artikal_id` int NOT NULL,
  `ocena` int NOT NULL DEFAULT '5',
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `recenzija_artikal_idx` (`artikal_id`),
  CONSTRAINT `recenzija_artikal` FOREIGN KEY (`artikal_id`) REFERENCES `podsis2`.`artikal` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `recenzija_chk_1` CHECK ((`ocena` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recenzija`
--

LOCK TABLES `recenzija` WRITE;
/*!40000 ALTER TABLE `recenzija` DISABLE KEYS */;
INSERT INTO `recenzija` VALUES (4,1,5,'odlican'),(5,1,5,NULL);
/*!40000 ALTER TABLE `recenzija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stavka` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artikal_id` int NOT NULL,
  `kolicina_art` int NOT NULL DEFAULT '1',
  `cena_artikla` decimal(20,3) NOT NULL,
  `narudzbina_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stavka_artikal_idx` (`artikal_id`),
  KEY `stavka_narudzbina_idx` (`narudzbina_id`),
  CONSTRAINT `stavka_artikal` FOREIGN KEY (`artikal_id`) REFERENCES `podsis2`.`artikal` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stavka_narudzbina` FOREIGN KEY (`narudzbina_id`) REFERENCES `narudzbina` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
INSERT INTO `stavka` VALUES (1,3,2,70000.000,1),(2,2,1,1000.000,1);
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transakcija`
--

DROP TABLE IF EXISTS `transakcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transakcija` (
  `id` int NOT NULL AUTO_INCREMENT,
  `narudzbina_id` int DEFAULT NULL,
  `placena_suma` decimal(20,3) DEFAULT NULL,
  `vreme_placanja` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `transakcija_narudzbina_idx` (`narudzbina_id`),
  CONSTRAINT `transakcija_narudzbina` FOREIGN KEY (`narudzbina_id`) REFERENCES `narudzbina` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transakcija`
--

LOCK TABLES `transakcija` WRITE;
/*!40000 ALTER TABLE `transakcija` DISABLE KEYS */;
INSERT INTO `transakcija` VALUES (1,1,141000.000,'2023-02-22 03:15:22');
/*!40000 ALTER TABLE `transakcija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-22  4:17:03
CREATE DATABASE  IF NOT EXISTS `podsis2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `podsis2`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: podsis2
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `opis` varchar(1023) NOT NULL,
  `cena` decimal(20,3) NOT NULL,
  `popust` int NOT NULL DEFAULT '0',
  `kategorija` int NOT NULL,
  `prodavac_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `art_kategorija_idx` (`kategorija`),
  KEY `art_prodavac_idx` (`prodavac_id`),
  CONSTRAINT `art_kategorija` FOREIGN KEY (`kategorija`) REFERENCES `kategorija` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `art_prodavac` FOREIGN KEY (`prodavac_id`) REFERENCES `podsis1`.`korisnik` (`korisnickoime`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` VALUES (1,'komoda','s fiokama',10000.000,5,1,'1'),(2,'sto','drveni',5000.000,0,1,'lola'),(3,'Lenovo ideapad530S','Polovan',70000.000,50,4,'1'),(4,'Lenovo ThinkPad X1',' ',60000.000,0,4,'1'),(5,'JBL slusalice','Nove',10000.000,10,9,'1');
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artikal_korpa`
--

DROP TABLE IF EXISTS `artikal_korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artikal_korpa` (
  `korpa_id` varchar(255) NOT NULL,
  `artikal_id` int NOT NULL,
  `kolicina` int NOT NULL,
  PRIMARY KEY (`korpa_id`,`artikal_id`),
  KEY `artikal_korpa_korpa_idx` (`korpa_id`),
  KEY `artikal_korpa_artikal_idx` (`artikal_id`),
  CONSTRAINT `artikal_korpa_artikal` FOREIGN KEY (`artikal_id`) REFERENCES `artikal` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `artikal_korpa_korpa` FOREIGN KEY (`korpa_id`) REFERENCES `korpa` (`korisnik_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal_korpa`
--

LOCK TABLES `artikal_korpa` WRITE;
/*!40000 ALTER TABLE `artikal_korpa` DISABLE KEYS */;
INSERT INTO `artikal_korpa` VALUES ('1',1,2),('pera',5,2);
/*!40000 ALTER TABLE `artikal_korpa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorija`
--

DROP TABLE IF EXISTS `kategorija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorija` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `nadkategorija_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kat_nadkategorija_idx` (`nadkategorija_id`),
  CONSTRAINT `kat_nadkategorija` FOREIGN KEY (`nadkategorija_id`) REFERENCES `kategorija` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija`
--

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;
INSERT INTO `kategorija` VALUES (1,'namestaj',NULL),(2,'posudje',NULL),(3,'Kompjuterska oprema',NULL),(4,'Laptop',3),(5,'Laptop',3),(6,'Slusalice',3),(7,'Tanjir',2),(8,'Casa',2),(9,'Bezicne slusalice',6);
/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korpa`
--

DROP TABLE IF EXISTS `korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korpa` (
  `korisnik_id` varchar(255) NOT NULL,
  `ukupna_cena` decimal(20,3) NOT NULL DEFAULT '0.000',
  PRIMARY KEY (`korisnik_id`),
  KEY `korpa_korisnik_id_idx` (`korisnik_id`),
  CONSTRAINT `korpa_korisnik_id` FOREIGN KEY (`korisnik_id`) REFERENCES `podsis1`.`korisnik` (`korisnickoime`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korpa`
--

LOCK TABLES `korpa` WRITE;
/*!40000 ALTER TABLE `korpa` DISABLE KEYS */;
INSERT INTO `korpa` VALUES ('1',19000.000),('pera',18000.000);
/*!40000 ALTER TABLE `korpa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-22  4:17:03
