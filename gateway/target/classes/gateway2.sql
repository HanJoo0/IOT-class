DROP TABLE SENSORS;

create table sensors(
	sensor_id 	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	value		REAL,		
    type  		TEXT NOT NULL,
    location	TEXT,
    reg_date	TEXT
);

INSERT INTO SENSORS(VALUE, TYPE, LOCATION, REGDATE)
VALUES(20.0, '온도', '거실', '2018-04-18');
INSERT INTO SENSORS(VALUE, TYPE, LOCATION, REGDATE)
VALUES(20.1, '온도', '거실', '2018-04-18');

select * from SENSORS;

commit;