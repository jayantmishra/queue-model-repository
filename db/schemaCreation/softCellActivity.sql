USE `softCellDb`;

--
-- Table structure for table `softcell_activity`
--


DROP TABLE IF EXISTS `softcell_activity`;

CREATE TABLE `softcell_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_status` int(11) NOT NULL DEFAULT 0,
  `activity_sub_status` int(11) NOT NULL DEFAULT 0,
  `department_id` int(11) NOT NULL DEFAULT 0,
  `assigned_to` int(11) NOT NULL DEFAULT 0,
  `description` varchar(45) default null,
  `queue_id` int(11) NOT NULL DEFAULT 0,
  `user_last_worked` int(11) NOT NULL DEFAULT 0,
  `locked` varchar(45) default 'n',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
