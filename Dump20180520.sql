-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: human_resources
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `job_request`
--

DROP TABLE IF EXISTS `job_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_request` (
  `jr_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идентификационный номер заявки на вакансию.',
  `jr_job_vacancy_id` int(11) unsigned NOT NULL COMMENT 'Идентификационный номер вакансии на работу, на которую подана заявка.',
  `jr_user_id` int(11) unsigned NOT NULL COMMENT 'Идентификационный номер пользователя, оставившего заявку на вакансию.',
  `jr_resume` text NOT NULL COMMENT 'Резюме, составленое соискателем, для вакансии.',
  `jr_status` enum('rejected','new','approved','added') NOT NULL DEFAULT 'new' COMMENT 'Статус заявки на вакансию (новая, одобренная, отказанная).',
  PRIMARY KEY (`jr_id`),
  KEY `fk_jr_jv_id_idx` (`jr_job_vacancy_id`),
  KEY `fk_jr_ap_id_idx` (`jr_user_id`),
  CONSTRAINT `fk_jr_job_vacancy_id` FOREIGN KEY (`jr_job_vacancy_id`) REFERENCES `job_vacancy` (`jv_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_jr_user_id` FOREIGN KEY (`jr_user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='Заявка на вакансию, добавленная соискателем и необходимая для принятия на на вакансию. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_request`
--

LOCK TABLES `job_request` WRITE;
/*!40000 ALTER TABLE `job_request` DISABLE KEYS */;
INSERT INTO `job_request` VALUES (17,51,41,'Work','rejected'),(18,55,41,'Give me work','added'),(19,51,41,'Give me some work','added'),(20,51,41,'Resume','added'),(21,56,41,'Ttttt','added');
/*!40000 ALTER TABLE `job_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_vacancy`
--

DROP TABLE IF EXISTS `job_vacancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_vacancy` (
  `jv_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идентификационный номер объвления об вакансии на работу.',
  `jv_organization_id` int(11) unsigned NOT NULL COMMENT 'Идентиикацинный номер организации, разместившей объявление.',
  `jv_name` varchar(150) NOT NULL COMMENT 'Наименование объявления о работе.',
  `jv_upload_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата добавления объявления.',
  `jv_requirement` text NOT NULL COMMENT 'Требования для приема на вакансию.',
  `jv_status` enum('open','close','new') NOT NULL DEFAULT 'new' COMMENT 'Статус вакансии на работу (открытая, закрытая, новая).',
  PRIMARY KEY (`jv_id`),
  KEY `fk_jv_o_id_idx` (`jv_organization_id`),
  CONSTRAINT `fk_jv_organization_id` FOREIGN KEY (`jv_organization_id`) REFERENCES `organization` (`o_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='Объвление о наборе на вакнсию в организацию.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_vacancy`
--

LOCK TABLES `job_vacancy` WRITE;
/*!40000 ALTER TABLE `job_vacancy` DISABLE KEYS */;
INSERT INTO `job_vacancy` VALUES (51,15,'Vac','2018-05-20 03:50:05','Vac req','open'),(53,15,'Vac','2018-05-18 19:22:19','Vac req','open'),(54,15,'New vacancy','2018-05-20 03:49:53','Requ','open'),(55,16,'Teacher','2018-05-19 16:41:29','Lol','close'),(56,15,'Programmer','2018-05-19 21:20:59','Write some code','close');
/*!40000 ALTER TABLE `job_vacancy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `o_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идентификационный номер организации.',
  `o_name` varchar(150) NOT NULL COMMENT 'Наименование организации.',
  `o_website` varchar(255) DEFAULT NULL COMMENT 'URL-адрес сайта организации.',
  `o_description` text NOT NULL COMMENT 'Описание деятельности организации .',
  `o_type` enum('commercial','noncommercial') NOT NULL DEFAULT 'commercial' COMMENT 'Тип организации (коммерческая, некоммерческая).',
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='Коммерческая или некоммерческая организация, от имени которой в систему добавлябтся объявления о наборе на вакансию.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'Belshina','www.belshina.by','Creation of tires','commercial'),(2,'Kommunarka','www.kommunarka.by','Creation of candies','commercial'),(3,'EPAM','www.epam.com','IT services','commercial'),(4,'Unicef','www.unicef.com','Donation','noncommercial'),(5,'Repka','https://www.youtube.com/watch?v=xpVfcZ0ZcFM&list=RDMMBPNTC7uZYrI&index=27','Lol','commercial'),(6,'Repka','https://www.youtube.com/watch?v=d-nnnwKxBJQ&index=24&list=RDMMBPNTC7uZYrI','Ok','noncommercial'),(7,'Unik','https://vk.com/feed?section=recommended','lul','commercial'),(8,'Unik','https://vk.com/feed?section=recommended','Omega lul','commercial'),(9,'Unik','https://vk.com/feed?section=recommended','Omega lul','commercial'),(10,'company','https://stackoverflow.com/questions/31223395/how-to-paginate-using-only-jstl-cforeach-without-javascript-or-jquery?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa','Some text','noncommercial'),(15,'MyOrg','https://www.youtube.com/watch?v=d-nnnwKxBJQ&index=24&list=RDMMBPNTC7uZYrI','Descr','commercial'),(16,'MyOrg','https://www.youtube.com/watch?v=d-nnnwKxBJQ&index=24&list=RDMMBPNTC7uZYrI','Descr','commercial');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Идентификационный номер пользователя.',
  `u_email` varchar(320) NOT NULL COMMENT 'Email-адрес пользователя, который используется как логин, для входа в систему.\n\nМаксимальный допустимый размер любого email-адреса - 320 символов.',
  `u_role` enum('admin','aspirant','worker','director') NOT NULL DEFAULT 'aspirant' COMMENT 'Роль пользователя в приложении (администратор, соискатель, сотрудник).',
  `u_password` varchar(45) NOT NULL COMMENT 'Пароль пользователя для входа в систему.',
  `u_firstname` varchar(45) NOT NULL COMMENT 'Имя пользователя.',
  `u_lastname` varchar(45) NOT NULL COMMENT 'Фамилия пользователя.',
  `u_organization_id` int(11) unsigned DEFAULT NULL COMMENT 'Идентификационный номер организации, заполняемый, если пользователь является сотрудником кадровой службы какой-либо организации.',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `ap_email_UNIQUE` (`u_email`),
  KEY `fk_o_id_idx` (`u_organization_id`),
  CONSTRAINT `fk_u_organization_id` FOREIGN KEY (`u_organization_id`) REFERENCES `organization` (`o_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='Пользователь приложения, зарегистрированный в системе.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (31,'admin@gmail.com','admin','36944ef9fe08dfc9cac3374e9dfd7f0','Admin','Admin',NULL),(38,'user@gmail.com','director','36944ef9fe08dfc9cac3374e9dfd7f0','Vladislav','Molchanov',15),(39,'jeka.9393@mail.ru','aspirant','36944ef9fe08dfc9cac3374e9dfd7f0','Vladislav','Molchanov',NULL),(40,'vladd1997xx@gmail.com','director','36944ef9fe08dfc9cac3374e9dfd7f0','Vladislav','Molchanov',16),(41,'newuser@gmail.com','aspirant','36944ef9fe08dfc9cac3374e9dfd7f0','Vladislav','Molchanov',NULL);
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

-- Dump completed on 2018-05-20 11:50:55
