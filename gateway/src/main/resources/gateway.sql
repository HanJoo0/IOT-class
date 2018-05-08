DROP TABLE SENSORS;

create table sensors(
	sensor_id 	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	value		REAL,
	name		TEXT,
    type  		TEXT NOT NULL,
    location	TEXT,
    reg_date	TEXT
);

SELECT * FROM SENSORS;
commit