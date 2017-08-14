USE `softCellDb`;

--
-- Table structure for table `softcell_routing_wat`
--

DROP TABLE IF EXISTS `softcell_routing_wat`;

CREATE TABLE `softcell_routing_wat` (
  `instance_id` INT (11) NOT NULL ,
  `min_id` INT (11) NOT NULL ,
  `max_id` INT (11) NOT NULL ,
  `working_status` INT (11) NOT NULL ,
	`aborted` INT (11) NOT NULL ,
  PRIMARY KEY (`instance_id`,  `min_id`, `max_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

