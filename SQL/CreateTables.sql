DROP TABLE IF EXISTS `bank`;
DROP TABLE IF EXISTS `signupthree`;
DROP TABLE IF EXISTS `signuptwo`;
DROP TABLE IF EXISTS `signup`;
DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `cardnumber` varchar(20) NOT NULL,
  `pin` varchar(10) NOT NULL,
  PRIMARY KEY (`cardnumber`)
) 

CREATE TABLE `signup` (
  `formno` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `father_name` varchar(20) DEFAULT NULL,
  `dob` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `marital_status` varchar(20) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `state` varchar(25) DEFAULT NULL,
  `pin` varchar(20) DEFAULT NULL
) 

CREATE TABLE `signuptwo` (
  `formno` varchar(20) NOT NULL,
  `religion` varchar(30) NOT NULL,
  `category` varchar(20) NOT NULL,
  `income` varchar(30) NOT NULL,
  `qualification` varchar(50) NOT NULL,
  `occupation` varchar(30) NOT NULL,
  `pan` varchar(20) NOT NULL,
  `aadhar` varchar(20) NOT NULL,
  `senior_citizen` varchar(3) NOT NULL,
  `existing_account` varchar(3) NOT NULL,
  PRIMARY KEY (`formno`)
) 

CREATE TABLE `signupthree` (
  `formno` varchar(20) NOT NULL,
  `estatement` varchar(5) DEFAULT NULL,
  `emailsms` varchar(5) DEFAULT NULL,
  `chequebook` varchar(5) DEFAULT NULL,
  `mobilebanking` varchar(5) DEFAULT NULL,
  `internetbanking` varchar(5) DEFAULT NULL,
  `atmcard` varchar(5) DEFAULT NULL,
  `savingaccount` varchar(5) DEFAULT NULL,
  `fixedepositaccount` varchar(5) DEFAULT NULL,
  `currentaccount` varchar(5) DEFAULT NULL,
  `recurringdepositaccount` varchar(5) DEFAULT NULL,
  `pin` char(4) NOT NULL,
  PRIMARY KEY (`formno`)
) 

CREATE TABLE `bank` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pin` varchar(10) NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `type` enum('Deposit','Withdrawal') NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)