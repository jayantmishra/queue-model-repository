USE `softCellDb`;

--
-- Table structure for table `softcell_routing_wat_lock`
--

DROP TABLE IF EXISTS `softcell_routing_wat_lock`;

CREATE TABLE `softcell_routing_wat_lock` (
  `locked` INT (11) NOT NULL ,
  `locked_by` INT (11) NOT NULL ,
  `lock_type` INT (11) NOT NULL ,

  PRIMARY KEY (`lock_type`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
