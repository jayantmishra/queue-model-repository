USE `softCellDb`;

--
-- Table structure for table `softcell_routing_variable`
--

DROP TABLE IF EXISTS `softcell_routing_variable`;

CREATE TABLE `softcell_routing_variable` (
  `routing_id` INT (11) NOT NULL auto_increment,
  `routing_obj_name` varchar(11) default NULL,
  `routing_var_name` varchar (45) default NULL,
  `routing_var_value` int(11) not null default 0,  
  PRIMARY KEY (`routing_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

