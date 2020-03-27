create table School (
	id int auto_increment
		primary key,
	name varchar(40) not null,
	street varchar(40) null,
	constraint School_name_uindex
		unique (name)
);

CREATE TABLE `Person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(100) NOT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `MatNr` varchar(100) DEFAULT NULL,
  `school_id` int not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Person_UN` (`MatNr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
;
ALTER TABLE Person ADD FOREIGN KEY (school_id)
        REFERENCES School(id);