USE `softCellDb`;

--
-- Table structure for table `softcell_queue`
--

DROP TABLE IF EXISTS `softcell_queue`;

CREATE TABLE `softcell_queue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT,
  `queue_name` varchar(45) DEFAULT NULL,
  `queue_state` int(11) NOT NULL DEFAULT 0,
  `department_id` int(11) NOT NULL DEFAULT 0,
  `queue_description` varchar(45) DEFAULT NULL,
  `queue_routing_type` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`queue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;


