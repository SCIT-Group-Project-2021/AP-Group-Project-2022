-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: jwrms_db
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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `fName` varchar(45) NOT NULL,
  `lName` varchar(45) NOT NULL,
  `dob` date DEFAULT NULL,
  `telephoneNum` varchar(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dateOfMembership` date NOT NULL,
  `dateOfMembershipExpiry` date NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=100020 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (100000,'Ashley','Deans','2002-06-12','18764523606','ashs4657@gmail.com','2022-11-03','2023-11-03'),(100001,'Ashley','Deans','2002-12-06','18764523606','ashs4657@gmail.com','2022-11-03','2023-11-03'),(100003,'Ashley','Deans','2002-12-06','18764523606','ashs4657@gmail.com','2022-11-03','2023-11-03'),(100005,'chase','Doe','2001-07-16','18765553606','gabe@gmail.com','2022-11-03','2023-11-03'),(100006,'chase','Doe','2001-07-16','18765553606','gabe@gmail.com','2022-11-03','2023-11-03'),(100007,'chase','Doe','2001-07-16','18765553606','gabe@gmail.com','2022-11-03','2023-11-03'),(100008,'chase','Doe','2001-07-16','18765553606','gabe@gmail.com','2024-11-03','2025-11-03'),(100009,'Sylvia','Palmer',NULL,'18768374632','','2022-11-13','2023-11-13'),(100010,'gdgd','dgdgd',NULL,'18764637263','','2022-11-13','2023-11-13'),(100011,'Ash','Ketchum','2022-11-02','18764325324','','2022-11-13','2022-11-13'),(100012,'Ramone','Brown',NULL,'15235235232','ash@gamil.com','2022-11-08','2023-11-08'),(100014,'Ashley','Deans','2002-06-12','18764523606','ashs4657@gmail.com','2022-09-13','2023-09-13'),(100015,'Gabriel','Tickle','2019-11-12','18769843532','tickle@gmail.com','2022-11-16','2023-11-16'),(100016,'James','Tickle','2019-11-12','18764442222','jTickle@gmail.com','2022-11-14','2023-11-14'),(100017,'Yemima','Tickle','2019-11-12','18764442222','jTickle@gmail.com','2022-11-14','2022-11-14'),(100018,'Sebastian','Tickle','2019-11-12','18764442222','jTickle@gmail.com','2022-11-14','2022-11-14'),(100019,'Gabriel','Tickle',NULL,'18769843123','','2022-11-16','2023-11-16');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 19:47:03
