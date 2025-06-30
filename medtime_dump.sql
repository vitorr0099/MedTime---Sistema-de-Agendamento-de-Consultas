-- MySQL dump 10.13  Distrib 9.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: medtime
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appointment_date_time` datetime(6) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `doctor_id` bigint NOT NULL,
  `patient_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmujeo4tymoo98cmf7uj3vsv76` (`doctor_id`),
  KEY `FK8exap5wmg8kmb1g1rx3by21yt` (`patient_id`),
  CONSTRAINT `FK6u6s6egu60m2cbdjno44jbipa` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK8exap5wmg8kmb1g1rx3by21yt` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`),
  CONSTRAINT `FKmujeo4tymoo98cmf7uj3vsv76` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`),
  CONSTRAINT `FKopb2h9yhin1rb4dqote8bws6w` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,'2025-06-18 17:30:00.000000','SCHEDULED',1,2),(2,'2025-06-18 17:00:00.000000','SCHEDULED',1,2),(3,'2025-06-19 17:30:00.000000','SCHEDULED',1,2),(4,'2025-06-18 16:30:00.000000','SCHEDULED',1,2),(5,'2025-06-28 08:00:00.000000','CANCELLED',1,2),(6,'2025-06-19 17:00:00.000000','SCHEDULED',1,2),(7,'2025-06-19 17:00:00.000000','SCHEDULED',2,2),(8,'2025-06-21 17:30:00.000000','SCHEDULED',1,2),(9,'2025-06-24 17:30:00.000000','SCHEDULED',1,2),(10,'2025-06-23 17:30:00.000000','SCHEDULED',4,2),(11,'2025-06-24 15:30:00.000000','SCHEDULED',5,2),(12,'2025-06-27 14:00:00.000000','SCHEDULED',2,3),(13,'2025-07-01 08:00:00.000000','SCHEDULED',5,4),(14,'2025-06-25 08:00:00.000000','SCHEDULED',2,5),(15,'2025-06-26 17:30:00.000000','CANCELLED',4,2),(16,'2025-06-24 17:00:00.000000','SCHEDULED',1,2),(17,'2025-06-27 17:30:00.000000','CANCELLED',2,2),(18,'2025-06-24 16:30:00.000000','SCHEDULED',1,2),(19,'2025-06-27 17:00:00.000000','CANCELLED',2,2),(20,'2025-06-27 08:00:00.000000','CANCELLED',2,2);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `crm` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `specialization_id` bigint DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fcr57y1el74vifvhh7dci90s` (`specialization_id`),
  CONSTRAINT `FK7fcr57y1el74vifvhh7dci90s` FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'225','Augusto','/logo/ayran.png',1,'O Dr. Augusto ├® um experiente cardiologista, formado em Medicina pela Universidade Cient├¡fica de GRU e com resid├¬ncia m├®dica em Cardiologia pelo Instituto Batimentos Perfeitos. Com mais de 15 anos de atua├º├úo na ├írea, ele ├® conhecido por seu profundo conhecimento em procedimentos complexos e diagn├│sticos precisos. Al├®m de seu trabalho cl├¡nico, Dr. Augusto ├® pesquisador em tecnologias de monitoramento card├¡aco e j├í publicou diversos artigos em revistas cient├¡ficas sobre arritmias e insufici├¬ncia card├¡aca. Apesar de seu jeito um pouco exc├¬ntrico e met├│dico, ├® extremamente dedicado aos pacientes e respeitado entre os colegas pela sua compet├¬ncia t├®cnica e inova├º├úo no tratamento de doen├ºas card├¡acas.',NULL),(2,'887','Rosana Gomes','/logo/samira.png',1,'A Doutora Rosana ├® uma renomada cardiologista, formada em Medicina pela Universidade de Sa├║de Infantil, com especializa├º├úo em Cardiologia Pedi├ítrica pelo Instituto Cora├º├úo Feliz. Com mais de 10 anos de experi├¬ncia na ├írea, ela se destaca por sua abordagem humanizada e seu cuidado especial com os pacientes mais jovens. Al├®m da atua├º├úo em hospitais de refer├¬ncia, participou de diversos congressos internacionais sobre sa├║de do cora├º├úo, sempre buscando as melhores pr├íticas e tecnologias para cuidar do bem-estar cardiovascular. ',NULL),(4,'888','Jo├úo M├írcio','/logo/henrique.png',1,'O Dr. Jo├úo M├írcio ├® um cardiologista altamente respeitado, formado em Medicina pela Universidade Federal do Cora├º├úo e com especializa├º├úo em Cardiologia Cl├¡nica pelo Instituto Vida Saud├ível. Ao longo de mais de 12 anos de carreira, construiu uma trajet├│ria marcada pela excel├¬ncia no atendimento, sempre aliando conhecimento t├®cnico a um olhar cuidadoso e emp├ítico com seus pacientes. Atuou em grandes hospitais e centros de refer├¬ncia, al├®m de participar ativamente de congressos e cursos de atualiza├º├úo na ├írea cardiovascular. Dr. Jo├úo M├írcio tamb├®m ├® reconhecido por seu trabalho em preven├º├úo de doen├ºas card├¡acas, promovendo palestras e a├º├Áes educativas voltadas para a conscientiza├º├úo sobre a sa├║de do cora├º├úo.',NULL),(5,'458','Valdir Jos├®','/logo/valdir.png',1,'O Dr. Valdir ├® um cardiologista de ampla experi├¬ncia, formado em Medicina pela Universidade Paulista de Ci├¬ncias da Sa├║de, com especializa├º├úo em Cardiologia Intervencionista pelo Instituto Cora├º├úo Forte. Com uma carreira de mais de 20 anos, ├® conhecido pela sua precis├úo em procedimentos como cateterismos, angioplastias e interven├º├Áes minimamente invasivas. Dr. Valdir se destaca n├úo apenas pela compet├¬ncia t├®cnica, mas tamb├®m pelo comprometimento com a sa├║de e qualidade de vida dos seus pacientes. Participa frequentemente de congressos nacionais e internacionais, buscando constante atualiza├º├úo nas inova├º├Áes da cardiologia. Al├®m da atua├º├úo cl├¡nica, tamb├®m dedica parte do seu tempo ├á forma├º├úo de novos profissionais da ├írea, sendo professor e mentor em programas de resid├¬ncia m├®dica.',NULL);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (2,NULL,'vitor@gmail','Vitor',NULL),(3,NULL,'ivone@gmail','Ivone Pereira',NULL),(4,NULL,'tarcisio@gmail','Tarcisio ',NULL),(5,NULL,'teste@gmail','teste',NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specializations`
--

DROP TABLE IF EXISTS `specializations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specializations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3lckmb5m8qfrxyyry2y6ovh9b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specializations`
--

LOCK TABLES `specializations` WRITE;
/*!40000 ALTER TABLE `specializations` DISABLE KEYS */;
INSERT INTO `specializations` VALUES (1,'Cardiologia','Especialidade do cora├º├úo'),(2,'Neurologia','Especialidade do c├®rebro'),(3,'Ginecologia','Especialidade da mulher'),(4,'Ortopedia','Especialidade dos ossos');
/*!40000 ALTER TABLE `specializations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `crm` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `user_type` enum('DOCTOR','PATIENT') NOT NULL,
  `specialization_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKdr5joptqk3x0b8sqo5xtq31dw` (`specialization_id`),
  CONSTRAINT `FKdr5joptqk3x0b8sqo5xtq31dw` FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'225','augusto@gmail','Augusto','123',NULL,'DOCTOR',1),(2,'087','rosana@gmail.com','Rosana','123',NULL,'DOCTOR',1),(3,NULL,'vitor@gmail','Vitor','123',NULL,'PATIENT',NULL),(4,'888','joaomarcio@gmail','Jo├úo M├írcio','123',' ','DOCTOR',1),(5,NULL,'valdir@gmail','Valdir','123',NULL,'DOCTOR',1),(6,NULL,'ivone@gmail','Ivone Pereira','123',NULL,'PATIENT',NULL),(7,NULL,'tarcisio@gmail','Tarcisio ','1939',NULL,'PATIENT',NULL),(8,NULL,'teste@gmail','teste','123',NULL,'PATIENT',NULL);
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

-- Dump completed on 2025-06-30  0:45:52
