-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'225','Augusto','/logo/ayran.png',1,'O Dr. Augusto é um experiente cardiologista, formado em Medicina pela Universidade Científica de GRU e com residência médica em Cardiologia pelo Instituto Batimentos Perfeitos. Com mais de 15 anos de atuação na área, ele é conhecido por seu profundo conhecimento em procedimentos complexos e diagnósticos precisos. Além de seu trabalho clínico, Dr. Augusto é pesquisador em tecnologias de monitoramento cardíaco e já publicou diversos artigos em revistas científicas sobre arritmias e insuficiência cardíaca. Apesar de seu jeito um pouco excêntrico e metódico, é extremamente dedicado aos pacientes e respeitado entre os colegas pela sua competência técnica e inovação no tratamento de doenças cardíacas.','augusto@gmail.com'),(2,'887','Rosana Gomes','/logo/samira.png',1,'A Doutora Rosana é uma renomada cardiologista, formada em Medicina pela Universidade de Saúde Infantil, com especialização em Cardiologia Pediátrica pelo Instituto Coração Feliz. Com mais de 10 anos de experiência na área, ela se destaca por sua abordagem humanizada e seu cuidado especial com os pacientes mais jovens. Além da atuação em hospitais de referência, participou de diversos congressos internacionais sobre saúde do coração, sempre buscando as melhores práticas e tecnologias para cuidar do bem-estar cardiovascular. ','rosana@gmail.com'),(4,'888','João Márcio','/logo/henrique.png',1,'O Dr. João Márcio é um cardiologista altamente respeitado, formado em Medicina pela Universidade Federal do Coração e com especialização em Cardiologia Clínica pelo Instituto Vida Saudável. Ao longo de mais de 12 anos de carreira, construiu uma trajetória marcada pela excelência no atendimento, sempre aliando conhecimento técnico a um olhar cuidadoso e empático com seus pacientes. Atuou em grandes hospitais e centros de referência, além de participar ativamente de congressos e cursos de atualização na área cardiovascular. Dr. João Márcio também é reconhecido por seu trabalho em prevenção de doenças cardíacas, promovendo palestras e ações educativas voltadas para a conscientização sobre a saúde do coração.','joaomarcio@gmail.com'),(5,'458','Valdir José','/logo/valdir.png',1,'O Dr. Valdir é um cardiologista de ampla experiência, formado em Medicina pela Universidade Paulista de Ciências da Saúde, com especialização em Cardiologia Intervencionista pelo Instituto Coração Forte. Com uma carreira de mais de 20 anos, é conhecido pela sua precisão em procedimentos como cateterismos, angioplastias e intervenções minimamente invasivas. Dr. Valdir se destaca não apenas pela competência técnica, mas também pelo comprometimento com a saúde e qualidade de vida dos seus pacientes. Participa frequentemente de congressos nacionais e internacionais, buscando constante atualização nas inovações da cardiologia. Além da atuação clínica, também dedica parte do seu tempo à formação de novos profissionais da área, sendo professor e mentor em programas de residência médica.','valdirjose@gmail.com'),(6,'987','Antonio da Silveira','/logo/antonio.png',2,'O Dr. Antonio da Silveira é um renomado neurologista, formado em Medicina pela Universidade de São Paulo (USP), com residência em Neurologia no Hospital das Clínicas e especialização em doenças neurodegenerativas. Com ampla experiência clínica e acadêmica, atua há mais de 15 anos no diagnóstico e tratamento de distúrbios neurológicos, sendo reconhecido por sua dedicação à pesquisa e ao cuidado humanizado com os pacientes.','antonio@gmail.com'),(7,'186','Vanessa Gomes','/logo/vanessa.png',2,'A Dra. Vanessa é uma dedicada neurologista, formada pela Universidade Federal do Paraná (UFPR), com residência médica em Neurologia pelo Hospital das Clínicas e especialização em neurologia clínica. Com sólida atuação na área há mais de uma década, destaca-se pelo atendimento cuidadoso e pela constante atualização em pesquisas relacionadas a epilepsia, esclerose múltipla e cefaleias.','vanessa@gmail.com'),(8,'882','Plinio dos Santos','/logo/plinio.png',4,'O Dr.Plínio dos Santos é um prestigiado neurologista, graduado em Medicina pela Universidade Federal de Minas Gerais (UFMG). Ele completou residência em Neurologia no Hospital das Clínicas daquela universidade e realizou uma especialização focada em neuromuscular e neuroimunologia. Com mais de 12 anos de experiência, o Dr. Plínio é reconhecido pelo atendimento atento e personalizado, além de sua atuação ativa em estudos clínicos sobre doenças autoimunes do sistema nervoso. Seu compromisso com a excelência no diagnóstico e tratamento de enfermidades neurológicas é combinado a uma abordagem acolhedora e centrada no bem-estar do paciente.','pliniodossantos@gmail.com'),(9,'354','Vinicius Silva','/logo/vinicius.png',4,'No coração de Joinville, atua o Dr. Vinicius Silveira, um ortopedista de excelência. Ele se formou em Medicina pela UFSC e fez residência em Ortopedia e Traumatologia no Hospital das Clínicas da USP. Sua especialização em cirurgia do ombro e cotovelo, feita no IOT, o torna um dos profissionais mais qualificados da região. Dedicado à atualização constante, o Dr. Vinicius busca sempre as técnicas mais modernas para seus pacientes.','vinicius@gmail.com'),(10,'077','Mariely','/logo/mariely.png',3,'A Dra. Mariely Alves é ginecologista, formada em Medicina pela Universidade Federal de Santa Catarina (UFSC), com residência médica em Ginecologia e Obstetrícia pelo Hospital Universitário da mesma instituição. Atua com foco na saúde da mulher, oferecendo acompanhamento humanizado em todas as fases da vida, com especial atenção à saúde reprodutiva, planejamento familiar e cuidados pré-natais. É reconhecida pela sua escuta atenta, profissionalismo e dedicação ao bem-estar das pacientes.','mariely@gmail.com'),(11,'354','Clarice Becker','/logo/clarice.png',3,'A Dra. Clarice Becker é uma experiente ginecologista, formada pela Universidade Estadual de Campinas (UNICAMP), com residência em Ginecologia e Obstetrícia no Hospital da Mulher da mesma universidade. Com sólida trajetória na medicina, dedica-se ao cuidado integral da saúde feminina, atuando com destaque em prevenção, tratamentos hormonais e acompanhamento gestacional. É conhecida por sua abordagem acolhedora, empatia no atendimento e constante atualização profissional.','claricebecker@gmail.com'),(12,'549','Jordana','/logo/jordana.png',3,'A Dra. Jordana Freitas é ginecologista, graduada em Medicina pela Universidade Federal do Rio Grande do Sul (UFRGS), com residência em Ginecologia e Obstetrícia no Hospital de Clínicas de Porto Alegre. Com forte atuação em saúde da mulher, especializou-se em medicina reprodutiva e cuidados no climatério. É reconhecida por seu atendimento ético e humano, oferecendo suporte completo e personalizado em todas as etapas da vida da paciente.','jordana@gmail.com');
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
INSERT INTO `specializations` VALUES (1,'Cardiologia','Especialidade do coração'),(2,'Neurologia','Especialidade do cérebro'),(3,'Ginecologia','Especialidade da mulher'),(4,'Ortopedia','Especialidade dos ossos');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'225','augusto@gmail','Augusto','123',NULL,'DOCTOR',1),(2,'087','rosana@gmail.com','Rosana','123',NULL,'DOCTOR',1),(3,NULL,'vitor@gmail','Vitor','123',NULL,'PATIENT',NULL),(4,'888','joaomarcio@gmail','João Márcio','123',' ','DOCTOR',1),(5,NULL,'valdir@gmail','Valdir','123',NULL,'DOCTOR',1),(6,'987','antonio@gmail','Antonio','123',NULL,'DOCTOR',2),(7,'185','vanessa@gmail.com','Vanessa Gomes','123',NULL,'DOCTOR',2),(8,'882','plinio@gmail.com','Plinio do Santos','123',NULL,'DOCTOR',2),(9,'354','vinicius@gmail.com','Vinicius','123',NULL,'DOCTOR',4),(10,'077','mariely@gmail.com','Mariely Alves','123',NULL,'DOCTOR',3),(11,'235','clarice@gmail.com','Clarice','123',NULL,'DOCTOR',3),(12,'549','jordana@gmail.com','Jordana','872',NULL,'DOCTOR',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'medtime'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 22:55:23
