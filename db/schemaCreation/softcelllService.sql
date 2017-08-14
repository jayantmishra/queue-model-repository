USE `softCellDb`;

--
-- Table structure for table `softcell_service`
--


DROP TABLE IF EXISTS `softcell_service`;

CREATE TABLE `softcell_service` (
  `service_id` INT (11) NOT NULL ,
  `service_name` varchar(45) default null ,
  `service_desc` varchar(45) default null ,
  `implementation_class` varchar(45) default null,

  PRIMARY KEY (`service_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
