USE `softCellDb`;

--
-- Table structure for table `softcell_user_load`
--

DROP TABLE IF EXISTS `softcell_user_load`;

CREATE TABLE `softcell_user_load` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `load_count` int(11) NOT NULL default 0,
 
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
