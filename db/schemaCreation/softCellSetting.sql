USE `softCellDb`;

--
-- Table structure for table `softcell_setting`
--

DROP TABLE IF EXISTS `softcell_setting`;

CREATE TABLE `softcell_setting` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `setting_name` varchar(45) DEFAULT NULL,
  `setting_description` varchar(45) DEFAULT NULL,
  `setting_value` int(11) not null,
  `apply` int(11) NOT NULL default 0,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
