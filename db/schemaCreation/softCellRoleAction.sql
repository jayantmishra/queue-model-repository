USE `softCellDb`;

--
-- Table structure for table `softcell_role_action`
--
DROP TABLE IF EXISTS `softcell_role_action`;

CREATE TABLE `softcell_role_action` (
  `role_id` INT (11) NOT NULL ,
  `action_id` INT (11) NOT NULL ,
  
  PRIMARY KEY (`role_id`) 
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


