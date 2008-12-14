/*
SQLyog Enterprise - MySQL GUI v5.19
Host - 5.0.22-community-nt : Database - e2pc
*********************************************************************
Server version : 5.0.22-community-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `e2pc`;

USE `e2pc`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `e2pc_user` */

DROP TABLE IF EXISTS `e2pc_user`;

CREATE TABLE `e2pc_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address` varchar(150) default NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(100) default NULL,
  `postal_code` varchar(15) NOT NULL,
  `province` varchar(100) default NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `account_enabled` bit(1) default NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_hint` varchar(255) default NULL,
  `phone_number` varchar(255) default NULL,
  `username` varchar(50) NOT NULL,
  `version` int(11) default NULL,
  `website` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `e2pc_user` */

insert into `e2pc_user` (`id`,`account_expired`,`account_locked`,`address`,`city`,`country`,`postal_code`,`province`,`credentials_expired`,`email`,`account_enabled`,`first_name`,`last_name`,`password`,`password_hint`,`phone_number`,`username`,`version`,`website`) values (-2,'\0','\0','','Denver','US','80210','CO','\0','matt@raibledesigns.com','','Matt','Raible','d033e22ae348aeb5660fc2140aec35850c4da997','Not a female kitty.','','admin',1,'http://raibledesigns.com'),(-1,'\0','\0','','Denver','US','80210','CO','\0','matt_raible@yahoo.com','','Tomcat','User','12dea96fec20593566ab75692c9949596833adc9','A male kitty.','','user',1,'http://tomcat.apache.org');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(64) default NULL,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert into `role` (`id`,`description`,`name`) values (-2,'Default role for all Users','ROLE_USER'),(-1,'Administrator role (can edit Users)','ROLE_ADMIN');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FK143BF46A4FD90D75` (`role_id`),
  KEY `FK143BF46AF503D155` (`user_id`),
  CONSTRAINT `FK143BF46A4FD90D75` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46AF503D155` FOREIGN KEY (`user_id`) REFERENCES `e2pc_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert into `user_role` (`user_id`,`role_id`) values (-2,-1),(-1,-2);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
