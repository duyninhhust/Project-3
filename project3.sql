-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: project3
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhq5aolak9ku5x5mx11cpjad9` (`user_id`),
  CONSTRAINT `FKqhq5aolak9ku5x5mx11cpjad9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'43 Trần Thiện Chánh, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Vietnam','nullnull','0967855433',2,2),(2,'43 Trần Thiện Chánh, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Vietnam','nullnull','0967855433',1,2),(3,'43 Trần Thiện Chánh, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Vietnam','nullnull','0967855433',1,1),(4,'43 Trần Thiện Chánh, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Vietnam','NinhNguyen','0967855433',1,6);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_phone`
--

DROP TABLE IF EXISTS `bill_phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `bill_id` int DEFAULT NULL,
  `phone_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7u6j9tptl0iiu0dw084b0m2de` (`bill_id`),
  KEY `FKig17rdhrpbem29h97btp8yef2` (`phone_id`),
  CONSTRAINT `FK7u6j9tptl0iiu0dw084b0m2de` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FKig17rdhrpbem29h97btp8yef2` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_phone`
--

LOCK TABLES `bill_phone` WRITE;
/*!40000 ALTER TABLE `bill_phone` DISABLE KEYS */;
INSERT INTO `bill_phone` VALUES (1,12,1,1,2),(2,12,1,2,2),(3,23,1,2,17),(4,100,3,3,16),(5,12,1,4,2);
/*!40000 ALTER TABLE `bill_phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Apple'),(3,'SamSung'),(4,'Huawei');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `phone_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfughhvr7y7nq948lgp3hg4fl8` (`phone_id`),
  CONSTRAINT `FKfughhvr7y7nq948lgp3hg4fl8` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chip` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `front_camera` varchar(255) DEFAULT NULL,
  `image` longtext,
  `introduction` varchar(1500) DEFAULT NULL,
  `name` varchar(300) NOT NULL,
  `old_price` double DEFAULT '0',
  `operating_system` varchar(255) DEFAULT NULL,
  `pin_charger` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` bigint NOT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `rear_camera` varchar(255) DEFAULT NULL,
  `rom` varchar(255) DEFAULT NULL,
  `screen` varchar(255) DEFAULT NULL,
  `sim` varchar(255) DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrd5ttn1av44o9m1ym5ub0pqxo` (`category_id`),
  CONSTRAINT `FKrd5ttn1av44o9m1ym5ub0pqxo` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,'sdfsd','0000-00-00','adas','https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone-11-pro-max_4_.jpg','Máy mới 100% , chính hãng Apple Việt Nam.dđss','iPhone 11 Pro Max Chính hãng',0,'sdf','1212',12,0,'sdfsdf','sdf','fsfd','sdfsd','3',1),(2,'Apple A14 Bionic',NULL,'adsasd','https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/l/a/layer_20.jpg','Máy mới 100% , chính hãng Apple Việt Nam.dđss','iPhone 11 I Chính hãng VN/A',0,'3969 mAh','112',12,1,'3 Camera 12MP:','512 GB','2688 x 1242 pixels','Nano-SIM + eSIM','1',1),(16,'Snapdragon 888 5G (5 nm)',NULL,'ádasd','https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/s/m/sm-f926_zfold3_5g_openback_phantomsilver_210611.jpg','Máy mới 100% , chính hãng Apple Việt Nam.dđss','Samsung Galaxy Z Fold3 5G',0,'Li-Po 4400 mAh','1800',100,9,'Camera góc siêu rộng: 12 MP, f/2.2 Camera màn hình phụ: 10MP, f/2.2','256 GB','7.6 inches','2 SIM (nano‑SIM và eSIM)','3',3),(17,'Apple A15',NULL,'ádasdads','https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/iphone_13-_pro-4_2_1.jpg','Máy mới 100% , chính hãng Apple Việt Nam.dđss','iPhone 13 Pro Max 256GB I Chính hãng VN/A',0,'4,325mAh','1700',23,5,'	Camera góc rộng: 12MP, ƒ/1.5 Camera góc siêu rộng: 12MP, ƒ/1.8 Camera tele : 12MP, /2.8','256 GB','OLED 6.7 inches','2 SIM (nano‑SIM và eSIM)','1',4);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'member');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nguyenduyninh99@gmail.com','123','admin',1,NULL,NULL),(2,'phuclythaito@gmail.com','1','duyninh',2,NULL,NULL),(3,'phuclythaito@gmail.com','123','thanhvinhcap20',2,NULL,NULL),(4,'mark@smartup.io','123','hello',2,NULL,NULL),(5,'mark@smartup.io','123','nhomda3',2,NULL,NULL),(6,'ninh.nd172739@sis.hust.edu.vn','123','member1',2,'Ninh','Nguyen');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-27 13:21:24
