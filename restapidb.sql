CREATE DATABASE  IF NOT EXISTS `restapidb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `restapidb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: restapidb
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mt_user`
--

DROP TABLE IF EXISTS `mt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(15) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `is_active` varchar(3) DEFAULT NULL,
  `modified_by` varchar(15) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_user`
--

LOCK TABLES `mt_user` WRITE;
/*!40000 ALTER TABLE `mt_user` DISABLE KEYS */;
INSERT INTO `mt_user` VALUES (1,NULL,'2020-06-18 14:24:18','Y',NULL,'2020-06-18 14:24:18',0,'Imyash30','imyash30');
/*!40000 ALTER TABLE `mt_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(15) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `is_active` varchar(3) DEFAULT NULL,
  `modified_by` varchar(15) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `d_o_b` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `mt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6n5vxlvjb9fao6qair8f09is9` (`mt_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (1,NULL,'2020-06-18 14:24:18','Y',NULL,'2020-06-18 15:00:53',2,'Virar','24','1997-03-30','yashdixit@gmail.com','Yash','Male','Dixit','1234567890','400001',1);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_education`
--

DROP TABLE IF EXISTS `user_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_education` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(15) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `is_active` varchar(3) DEFAULT NULL,
  `modified_by` varchar(15) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `certification` varchar(255) DEFAULT NULL,
  `passed_year` int(11) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `mt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdyp2urls9dgqh3m5s3lq0don1` (`mt_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_education`
--

LOCK TABLES `user_education` WRITE;
/*!40000 ALTER TABLE `user_education` DISABLE KEYS */;
INSERT INTO `user_education` VALUES (1,NULL,'2020-06-18 14:24:18','Y',NULL,'2020-06-18 14:24:18',0,'Java',2015,'BE',1);
/*!40000 ALTER TABLE `user_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_employement`
--

DROP TABLE IF EXISTS `user_employement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_employement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(15) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `is_active` varchar(3) DEFAULT NULL,
  `modified_by` varchar(15) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `d_o_j` date DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `technology_name` varchar(255) DEFAULT NULL,
  `years_of_exp` int(11) DEFAULT NULL,
  `mt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xgxjg12ri0vnopnpsnwb580r` (`mt_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_employement`
--

LOCK TABLES `user_employement` WRITE;
/*!40000 ALTER TABLE `user_employement` DISABLE KEYS */;
INSERT INTO `user_employement` VALUES (1,NULL,'2020-06-18 14:24:18','Y',NULL,'2020-06-18 14:24:18',0,'NeoSoft','2018-07-20','Developer',300000,'java',2,1);
/*!40000 ALTER TABLE `user_employement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-18 15:18:09
