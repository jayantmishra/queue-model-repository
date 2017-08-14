USE `softCellDb`;

--
-- Table structure for table `softcell_department`
--




DROP TABLE IF EXISTS `softcell_department`;

CREATE TABLE `softcell_department` (
  `department_id` INT (11) NOT NULL auto_increment,
  `department_name` varchar(45) default null,
  `department_desc`varchar(45) default null,
  PRIMARY KEY (`department_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
