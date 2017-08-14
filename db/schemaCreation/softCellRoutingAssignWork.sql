USE `softCellDb`;

--
-- Table structure for table `softcell_routing_assign_work`
--



DROP TABLE IF EXISTS `softcell_routing_assign_work`;

CREATE TABLE `softcell_routing_assign_work` (
  `ID` INT (11) NOT NULL auto_increment,
  `activity_id` int(11) NOT NULL default 1000,
  `department_id` int(11) not null default 999,
  PRIMARY KEY (`ID`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

