-- MySQL dump 10.16  Distrib 10.1.28-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: lojavirtual
-- ------------------------------------------------------
-- Server version	10.1.28-MariaDB

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `codigo_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Eletrodoméstivos'),(2,'Informática'),(3,'Móveis'),(4,'Came, mesa e banho'),(5,'Celulares'),(6,'Eletrônicos'),(7,'Escritório');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `municipio` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo_cliente`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'35314131390','danielsouza553@gmail.com','Rua x','RJ',NULL,'Rio de Janeiro','Daniel Souza','$2a$10$crRycBhWQPgmu/pDwhlIzu2wW6DqTuqmdLxUhU1Jtd8UeWanEkQ/i','(21)9821-0192'),(2,'25093295884','daniel.cavalcanti@outlook.com.br','Rua y','SP',NULL,'São Paulo','Marcia Gomes','$2a$10$idwARSFZrxofFJnRJW2hPO/cTb/kHfy4QXozWPGYRhvyL7gxiwr52','(11)2133-2333');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `codigo_historico` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `produto_codigo_produto` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_historico`),
  KEY `FKd5976bwj4ngv4ivelcqskog4r` (`produto_codigo_produto`),
  CONSTRAINT `FKd5976bwj4ngv4ivelcqskog4r` FOREIGN KEY (`produto_codigo_produto`) REFERENCES `produto` (`codigo_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (1,'2018-02-18 00:32:21',4,1,1),(2,'2018-02-18 00:32:21',4,1,2),(3,'2018-02-18 00:32:21',4,2,3),(4,'2018-02-18 00:32:21',4,1,4),(5,'2018-02-18 00:32:21',4,1,5),(6,'2018-02-18 00:32:21',4,2,6),(7,'2018-02-18 00:32:21',4,1,7),(8,'2018-02-18 00:32:21',4,1,8);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pedido` (
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `codigo_pedido_pedido` int(11) NOT NULL,
  `codigo_produto_produto` int(11) NOT NULL,
  PRIMARY KEY (`codigo_pedido_pedido`,`codigo_produto_produto`),
  KEY `FKsxr0kcoqfcdauwn2eife01w5h` (`codigo_produto_produto`),
  CONSTRAINT `FKjgd4vkg0i8mpi0f2xra85cwc2` FOREIGN KEY (`codigo_pedido_pedido`) REFERENCES `pedido` (`codigo_pedido`),
  CONSTRAINT `FKsxr0kcoqfcdauwn2eife01w5h` FOREIGN KEY (`codigo_produto_produto`) REFERENCES `produto` (`codigo_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pedido`
--

LOCK TABLES `item_pedido` WRITE;
/*!40000 ALTER TABLE `item_pedido` DISABLE KEYS */;
INSERT INTO `item_pedido` VALUES (200,1,1,1),(200,1,1,2),(200,1,1,3),(200,1,2,1),(200,1,3,1),(200,2,3,3);
/*!40000 ALTER TABLE `item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento` (
  `pedido_codigo_pedido` int(11) NOT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `numero_cartao` varchar(255) DEFAULT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`pedido_codigo_pedido`),
  CONSTRAINT `FK13q0025ggjchmteavxcogs2ui` FOREIGN KEY (`pedido_codigo_pedido`) REFERENCES `pedido` (`codigo_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (1,'2018-02-18 00:32:21','1234123412341234',10000),(2,'2018-02-18 00:32:21','1234123412341234',500),(3,'2018-02-18 00:33:54','10222',600);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `codigo_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `data_pedido` datetime DEFAULT NULL,
  `cliente_codigo_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_pedido`),
  KEY `FKt8t83645o7ymg9wgqeo45v70` (`cliente_codigo_cliente`),
  CONSTRAINT `FKt8t83645o7ymg9wgqeo45v70` FOREIGN KEY (`cliente_codigo_cliente`) REFERENCES `cliente` (`codigo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2018-02-18 00:32:21',1),(2,'2018-02-18 00:32:21',2),(3,'2018-02-18 00:33:54',1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfis`
--

DROP TABLE IF EXISTS `perfis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfis` (
  `cliente_codigo_cliente` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL,
  KEY `FKgn0srg7g7fb4f74dotxlim40a` (`cliente_codigo_cliente`),
  CONSTRAINT `FKgn0srg7g7fb4f74dotxlim40a` FOREIGN KEY (`cliente_codigo_cliente`) REFERENCES `cliente` (`codigo_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfis`
--

LOCK TABLES `perfis` WRITE;
/*!40000 ALTER TABLE `perfis` DISABLE KEYS */;
INSERT INTO `perfis` VALUES (1,1),(1,2),(2,2);
/*!40000 ALTER TABLE `perfis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `quantidade_estoque` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Computador',200,3),(2,'Liquidificador',200,3),(3,'Celular Samsung',200,3),(4,'SmartTV',200,3),(5,'Ventilador',200,3),(6,'Guarda Roupa',200,3),(7,'Travesseiro',200,3),(8,'Cadeira Giratória',200,3),(9,'Celular LG',200,3),(10,'Poltrona',200,3);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_categoria`
--

DROP TABLE IF EXISTS `produto_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_categoria` (
  `produto_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  KEY `FKq3g33tp7xk2juh53fbw6y4y57` (`categoria_id`),
  KEY `FK1c0y58d3n6x3m6euv2j3h64vt` (`produto_id`),
  CONSTRAINT `FK1c0y58d3n6x3m6euv2j3h64vt` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`codigo_produto`),
  CONSTRAINT `FKq3g33tp7xk2juh53fbw6y4y57` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`codigo_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_categoria`
--

LOCK TABLES `produto_categoria` WRITE;
/*!40000 ALTER TABLE `produto_categoria` DISABLE KEYS */;
INSERT INTO `produto_categoria` VALUES (1,1),(2,2),(2,3),(3,5),(4,6),(5,6),(6,3),(7,4),(8,7),(9,5),(10,3);
/*!40000 ALTER TABLE `produto_categoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-05  4:15:34
