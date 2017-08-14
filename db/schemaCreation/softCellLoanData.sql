USE `softCellDb`;

--
-- Table structure for table `softcell_loan_data`
--



DROP TABLE IF EXISTS `softcell_loan_data`;

CREATE TABLE `softcell_loan_data` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL DEFAULT 1000,
  `amount` int(11) NOT NULL DEFAULT 0,
  `tenure` int(11) NOT NULL DEFAULT 0,
  `customer_income` int(11) NOT NULL DEFAULT 0,
  `marital_status` varchar(45) default null,
  `Gender` varchar(45) default 'M',
  `age` int(11) NOT NULL DEFAULT 25,
  `race` varchar(45) default null,
  `education` varchar(45) default null,
  `employment_status` varchar(45) default null,
  PRIMARY KEY (`loan_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

