CREATE TABLE `Person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(100) NOT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `MatNr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Person_UN` (`MatNr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
;