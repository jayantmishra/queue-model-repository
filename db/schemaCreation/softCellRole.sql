USE `softCellDb`;

--
-- Table structure for table `softcell_role`
--
DROP TABLE IF EXISTS `softcell_role`;

CREATE TABLE `softcell_role` (
  `role_id` INT (11) NOT NULL ,
  `role_name` varchar(45) default null ,
  `role_description` varchar(45) default null ,
  `department_id` INT (11) NOT NULL ,

  PRIMARY KEY (`role_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

