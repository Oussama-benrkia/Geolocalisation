-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_geo
CREATE DATABASE IF NOT EXISTS `db_geo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_geo`;


CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `datcrt` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('User','Admin') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `user` (`id`, `last_name`, `datcrt`, `email`, `first_name`, `image`, `password`, `role`) VALUES
	(1, 'admin', '2024-04-19 16:34:09.452931', 'Admin@Admin.com', 'admin', '', '$2a$10$y7FA6d48NTSC4GAd0UD6Cem3X/yyCdUgt/ORoq7dxV4tDcZQI/Age', 'Admin');


CREATE TABLE IF NOT EXISTS `vehicule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_crt` datetime(6) DEFAULT NULL,
  `date_upt` datetime(6) DEFAULT NULL,
  `etat` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `status` enum('En_location','En_Panne','En_cours_de_réparation','En_new') DEFAULT NULL,
  `type` enum('A','B','C','D','EB','EC','ED') DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b8fyikkqe8bflk5vh1r7mdlda` (`matricule`),
  KEY `FKcujnirabp3j0fynvif9btxruf` (`user_id`),
  CONSTRAINT `FKcujnirabp3j0fynvif9btxruf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `vehicule` (`id`, `date_crt`, `date_upt`, `etat`, `image`, `matricule`, `modele`, `nom`, `status`, `type`, `user_id`) VALUES
	(1, NULL, NULL, b'0', NULL, 'LL456LL', 'Camaro', 'Toyota', 'En_new', 'EB', NULL),
	(7, NULL, '2024-05-23 21:16:05.184746', b'0', NULL, 'DD012DD', 'Corolla', 'Chevrolet', 'En_location', 'D', NULL),
	(9, NULL, NULL, b'0', NULL, 'FF678FF', 'Model S', 'Peugeot', 'En_Panne', 'EC', NULL),
	(10, NULL, NULL, b'1', NULL, 'GG901GG', 'Camaro', 'Toyota', 'En_cours_de_réparation', 'ED', NULL),
	(11, NULL, NULL, b'0', NULL, 'HH234HH', 'Focus', 'Ford', 'En_new', 'A', NULL),
	(12, NULL, '2024-05-24 10:26:13.280404', b'0', NULL, 'II567II', 'Corolla', 'Chevrolet', 'En_location', 'B', NULL),
	(13, NULL, NULL, b'0', NULL, 'JJ890JJ', '208', 'Tesla', 'En_Panne', 'C', NULL),
	(14, NULL, NULL, b'1', NULL, 'KK123KK', 'Model S', 'Peugeot', 'En_cours_de_réparation', 'D', NULL),
	(16, NULL, NULL, b'1', NULL, 'MM789MM', 'Focus', 'Ford', 'En_location', 'EC', NULL),
	(17, NULL, NULL, b'0', NULL, 'NN012NN', 'Corolla', 'Chevrolet', 'En_Panne', 'ED', NULL),
	(18, NULL, NULL, b'1', NULL, 'OO345OO', '208', 'Tesla', 'En_cours_de_réparation', 'A', NULL),
	(19, NULL, NULL, b'0', NULL, 'PP678PP', 'Model S', 'Peugeot', 'En_new', 'B', NULL),
	(20, NULL, NULL, b'1', NULL, 'QQ901QQ', 'Camaro', 'Toyota', 'En_location', 'C', NULL),
	(21, NULL, NULL, b'0', NULL, 'RR234RR', 'Focus', 'Ford', 'En_Panne', 'D', NULL),
	(27, '2024-05-24 18:04:46.068797', '2024-05-25 22:10:09.863599', b'0', 'Cars/4dace453-d8fc-4987-b25f-82c04dbffca5.jpg', 'abc123', '123', 'abc', 'En_Panne', 'A', 1);


CREATE TABLE IF NOT EXISTS `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER','OTHER_TYPE') DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `token` (`id`, `expired`, `revoked`, `token`, `token_type`, `user_id`) VALUES
	(8, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NDk3MDc1LCJleHAiOjE3MTcxMDE4NzV9.pp58qYO7FdZP1kAvN64gQzS6nK-lwk2lmGv-GtzGxdE', 'BEARER', 1),
	(9, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NDk5MjM1LCJleHAiOjE3MTcxMDQwMzV9.qQtxv8qxfEwVxpdjQ5QFTj8MxqihKmaE0DMsNm0b5hI', 'BEARER', 1),
	(10, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NTM2NTkwLCJleHAiOjE3MTcxNDEzOTB9.TBYZxooz6YNH_cXY9t5jLMvfmXZKDOt8fRqugfPJ0co', 'BEARER', 1),
	(11, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NTYxMTc1LCJleHAiOjE3MTcxNjU5NzV9.8I55RASHqMyop12OSoN4ExX6JKCti-dQLZrTNyRCPAU', 'BEARER', 1),
	(12, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NTY4NTg3LCJleHAiOjE3MTcxNzMzODd9.dj9sPxRGdARNsv_uPganKuLBTSIKO6jSUedQxEJtHAw', 'BEARER', 1),
	(13, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NTY5NDc5LCJleHAiOjE3MTcxNzQyNzl9.JXexWAAWD7Z6uakhhGkmC6BhkZWF-zgV-dg5G2v-nwg', 'BEARER', 1),
	(14, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwic3ViIjoiQWRtaW5AQWRtaW4uY29tIiwiaWF0IjoxNzE2NTcwMjM1LCJleHAiOjE3MTcxNzUwMzV9.PVoYZxKGZMOLGl-lHyTqO0V1PLUoRYNDJVyQKfykhRg', 'BEARER', 1);

CREATE TABLE IF NOT EXISTS `location_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_crt` datetime(6) DEFAULT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `vehicule_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7nriyo79u8apy8fr1ulic8n4l` (`vehicule_id`),
  CONSTRAINT `FK7nriyo79u8apy8fr1ulic8n4l` FOREIGN KEY (`vehicule_id`) REFERENCES `vehicule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `location_data` (`id`, `date_crt`, `x`, `y`, `vehicule_id`) VALUES
	(1, '2024-05-24 08:00:00.000000', 31.63, -8.008889, 1),
	(2, '2024-05-24 08:05:00.000000', 31.6305, -8.009, 1),
	(3, '2024-05-24 08:10:00.000000', 31.631, -8.0095, 1),
	(4, '2024-05-24 08:15:00.000000', 31.6315, -8.01, 1),
	(5, '2024-05-24 08:20:00.000000', 31.632, -8.0105, 1),
	(6, '2024-05-24 08:25:00.000000', 31.6325, -8.011, 1),
	(7, '2024-05-24 08:30:00.000000', 31.633, -8.0115, 1),
	(8, '2024-05-24 08:35:00.000000', 31.6335, -8.012, 1),
	(9, '2024-05-24 08:40:00.000000', 31.634, -8.0125, 1),
	(10, '2024-05-24 08:45:00.000000', 31.6345, -8.013, 1),
	(216, '2024-05-23 17:13:47.629888', 31.618732, -8.048389, 7),
	(217, '2024-05-23 17:13:47.629888', 31.618732, -8.048389, 7),
	(218, '2024-05-23 17:15:12.944762', 31.618761, -8.048387, 7),
	(219, '2024-05-23 17:15:17.924006', 31.618742, -8.048392, 7),
	(274, '2024-05-23 21:15:04.051787', 31.60505, -8.048397, 7),
	(275, '2024-05-23 21:15:08.673833', 31.61887, -8.99, 7),
	(276, '2024-05-23 21:15:13.758249', 31.618733, -8.048402, 9),
	(277, '2024-05-23 21:15:18.785798', 31.618744, -8.048394, 10),
	(278, '2024-05-23 21:15:33.753162', 31.618704, -8.048392, 20),
	(279, '2024-05-23 21:15:44.259598', 31.618748, -8.048386, 18),
	(280, '2024-05-23 21:15:49.064599', 31.618739, -8.0484, 16),
	(281, '2024-05-23 21:16:05.211919', 31.695, -8.0484, 9),
	(290, '2024-05-24 18:05:49.044253', 31.631412, -8.012724, 27),
	(291, '2024-05-24 18:06:18.303598', 31.631421, -8.012711, 27),
	(292, '2024-05-24 18:06:23.077316', 31.631412, -8.012732, 27),
	(293, '2024-05-24 18:06:43.285581', 31.63142, -8.012745, 27),
	(294, '2024-05-25 22:10:00.120330', 31.618735, -8.048395, 27),
	(295, '2024-05-25 22:10:08.435749', 31.618731, -8.048366, 27),
	(296, '2024-05-25 22:10:09.871019', 31.618731, -8.048366, 27);
