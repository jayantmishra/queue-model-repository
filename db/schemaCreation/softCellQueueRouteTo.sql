USE `softCellDb`;

--
-- Table structure for table `softcell_queue_route_to`
--
DROP TABLE IF EXISTS `softcell_queue_route_to`;

CREATE TABLE `softcell_queue_route_to` (
  `queue_id` int(11) NOT NULL ,
  `user_id` int(11) NOT NULL ,
	`route_when` int(11) not null default 0,
  PRIMARY KEY (`queue_id`,`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
