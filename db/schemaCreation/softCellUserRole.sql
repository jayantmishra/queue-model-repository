USE `softCellDb`;

--
-- Table structure for table `softcell_user_role`
--
DROP TABLE IF EXISTS `softcell_user_role`;

CREATE TABLE `softcell_user_role` (
  `role_id` INT (11) NOT NULL ,
  `user_id` INT (11) NOT NULL ,
  
  PRIMARY KEY (`role_id`) 
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

