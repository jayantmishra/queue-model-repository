USE `softCellDb`;

--
-- Table structure for table `softcell_routing_work`
--



DROP TABLE IF EXISTS `softcell_routing_work`;

CREATE TABLE `softcell_routing_work` (
  `ID` INT (11) NOT NULL auto_increment,
  `activity_id` int(11) NOT NULL default 1000,
  PRIMARY KEY (`ID`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

