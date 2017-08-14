USE `softCellDb`;

--
-- Table structure for table `softcell_process`
--

DROP TABLE IF EXISTS `softcell_process`;

CREATE TABLE `softcell_process` (
  `process_id` INT (11) NOT NULL ,
  `process_name` varchar(45) default null ,
  `state` int(11) default 0,
  `service_id` INT (11) NOT NULL ,
  `max_instances` INT(11) not null,
  PRIMARY KEY (`process_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

