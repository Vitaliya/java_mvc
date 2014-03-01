DROP TABLE Permit CASCADE;
DROP TABLE Transport CASCADE;
DROP TABLE Buyer CASCADE;
DROP TABLE Season CASCADE;
DROP TABLE Room CASCADE;
DROP TABLE Hotel_Provider CASCADE;
DROP TABLE Provider CASCADE;
DROP TABLE Hotel CASCADE;

CREATE TABLE Buyer
(
	id	  BIGINT  NOT NULL ,
	name_buyer	  VARCHAR(64)  NULL ,
	surname		  VARCHAR(64)  NULL ,
	middle_name	  VARCHAR(64)  NULL ,
	passport_data	  VARCHAR(64)  NULL ,
	zagran_passport	  VARCHAR(64)  NULL ,
	login		  VARCHAR(64)  NULL ,
	password	  VARCHAR(64)  NULL ,
 PRIMARY KEY (id)
);

CREATE TABLE Hotel
(
	id	  BIGINT  NOT NULL ,
	name_hotel	  VARCHAR(64)  NULL ,
	country		  VARCHAR(64)  NULL ,
	stars		  INTEGER  NULL ,
	town		  VARCHAR(64)  NULL ,
 PRIMARY KEY (id)
);

CREATE TABLE Hotel_Provider
(
	id		  BIGINT  NOT NULL ,
	id_hotel	  BIGINT  NOT NULL ,
	id_provider	  BIGINT  NOT NULL ,
	limit_of_time	  DATE  NULL ,
	dogovor		  VARCHAR(1024)  NULL ,
 PRIMARY KEY (id)
);

CREATE TABLE Permit
(
	id	  BIGINT  NOT NULL ,
	id_provider	  BIGINT  NOT NULL ,
	id_season	  BIGINT  NOT NULL ,
	id_transport	BIGINT  NOT NULL ,
	id_buyer	  BIGINT  NOT NULL ,
	id_room		BIGINT  NOT NULL ,
	final_sum	  DECIMAL(10,2)  NULL ,
	visa		DECIMAL(10,2)  NULL ,
	start_date 			DATE NULL,
	period			INTEGER NULL, 		
 PRIMARY KEY (id)
);

CREATE TABLE Provider
(
	id	  BIGINT  NOT NULL ,
	name_provider	  VARCHAR(64)  NULL ,
 PRIMARY KEY (id)
);

CREATE TABLE Room
(
	id		  BIGINT  NOT NULL ,
	id_hotel	  BIGINT  NOT NULL ,
	bar		  CHAR  NULL ,
	cost		  DECIMAL(10,2)  NULL ,
	num		  INTEGER  NULL ,
	full_room	  INTEGER  NULL ,
	type_of_room	  INTEGER  NULL ,	
 PRIMARY KEY (id)
);

CREATE TABLE Season
(
	id	  BIGINT  NOT NULL ,
	name_season	  VARCHAR(64)  NULL ,
	percent_sum	  DECIMAL(10,2)  NULL ,
 PRIMARY KEY (id)
);

CREATE TABLE Transport
(
	id	  BIGINT  NOT NULL ,
	sum_transport	  DECIMAL(10,2)  NULL ,
	type_of_transport  VARCHAR(64)  NULL ,
 PRIMARY KEY (id)
);

ALTER TABLE Hotel_Provider ADD FOREIGN KEY (id_hotel) REFERENCES Hotel(id);
ALTER TABLE Hotel_Provider ADD FOREIGN KEY (id_provider) REFERENCES Provider(id);
ALTER TABLE Permit ADD FOREIGN KEY (id_provider) REFERENCES Provider(id);
ALTER TABLE Permit ADD FOREIGN KEY (id_season) REFERENCES Season(id);
ALTER TABLE Permit ADD FOREIGN KEY (id_buyer) REFERENCES Buyer(id);
ALTER TABLE Permit ADD FOREIGN KEY (id_transport) REFERENCES Transport(id);
ALTER TABLE Permit ADD FOREIGN KEY (id_room) REFERENCES Room(id);
ALTER TABLE Room ADD FOREIGN KEY (id_hotel) REFERENCES Hotel(id);
