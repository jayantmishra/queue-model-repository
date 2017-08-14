USE `softCellDb`;

--
-- Table structure for table `softcell_acl`
--
DROP TABLE IF EXISTS `softcell_acl`;

CREATE TABLE `softcell_acl` (
  `acl_id` INT (11) NOT NULL ,
  `resource_id` INT (11) NOT NULL ,
  `resource_type` varchar(45) default null,
  `department_id` int(11) not null,
  PRIMARY KEY (`acl_id`) 
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

