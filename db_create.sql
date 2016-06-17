CREATE DATABASE crowdsound;
USE crowdsound;

CREATE TABLE playlist
(
	pl_cd			VARCHAR( 5 ) NOT NULL PRIMARY KEY,
	host_name		VARCHAR( 256 ) NOT NULL,
	time_created	TIMESTAMP,
	total_people	SMALLINT,
	songs			VARCHAR( 2048 )
);

CREATE TABLE people
(
	pl_cd			SMALLINT NOT NULL,
	person_name		VARCHAR( 256 ),
	artists			VARCHAR( 2048 ),
	genres			VARCHAR( 1024 ),
	songs			VARCHAR( 2048 )		
);

CREATE TABLE genre
(
	g_row			SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	genre			VARCHAR( 128 )
);


CREATE TABLE artist
(
	a_id			VARCHAR( 32 ) NOT NULL PRIMARY KEY,
	artist_nm		VARCHAR( 256 )
);