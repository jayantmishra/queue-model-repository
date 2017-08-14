USE `softCellDb`;

--
-- Table structure for table `softcell_instance`
--


DROP TABLE IF EXISTS `softcell_instance`;

CREATE TABLE `softcell_instance` (
  `instance_id` INT (11) NOT NULL ,
  `instance_name` varchar(45) default null ,
  `service_id` int(11) default 0,
  `state` INT (11) NOT NULL default 0,

  PRIMARY KEY (`instance_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
