USE `softCellDb`;

--
-- Table structure for table `softcell_action`
--
DROP TABLE IF EXISTS `softcell_action`;

CREATE TABLE `softcell_action` (
  `action_id` INT (11) NOT NULL ,
  `action_name` varchar(45) default null ,
  `action_desc` varchar(45) default null ,
  `action_type`  varchar(45) default 'user' ,
	`bit_number` INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`action_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



