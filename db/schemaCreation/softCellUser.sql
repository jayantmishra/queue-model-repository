USE `softCellDb`;

--
-- Table structure for table `softcell_user`
--

DROP TABLE IF EXISTS `softcell_user`;

CREATE TABLE `softcell_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `department_id` int(11) NOT NULL,
  `user_state` int(3) NOT null,
  `password` varchar(45) default NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
