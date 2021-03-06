CREATE DATABASE IF NOT EXISTS `shoppingcart`;
USE `shoppingcart`;


DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `cart`;


CREATE TABLE `product` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` smallint(6) DEFAULT NULL,
  `item_id` smallint(6) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `quantity` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_fk_idx` (`uid`),
  KEY `item_id_fk_idx` (`item_id`),
  CONSTRAINT `item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into user(name) values('bala');
insert into user(name) values('kanishk');
insert into user(name) values('jayanth');


insert into product(name, description) values('samsung tv', '47" UHD');
insert into product(name, description) values('blue ray player', 'sony with sound bar');

