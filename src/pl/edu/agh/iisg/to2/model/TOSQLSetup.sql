

CREATE TABLE IProject (
	id			INTEGER AUTO_INCREMENT PRIMARY KEY,
	projectId	INTEGER UNIQUE,
	deadline	DATE,
	startDate	DATE,
	budget		INTEGER
);

CREATE TABLE IProject_Team (
	id			INTEGER AUTO_INCREMENT PRIMARY KEY,
	teamId		INTEGER,
	projectId	INTEGER,
	FOREIGN KEY (projectId) REFERENCES IProject(projectId)
);


--DROP TABLE IProject;
--------------------------------------------------------------------
------- TEGO NIE TWORZYMY. To powinno byc w innych modulach--------
-------------------------------------------------------------------- 
--CREATE TABLE IEmployee (
--	id			INT AUTO_INCREMENT PRIMARY KEY,
--	employeeId	INT,
--	firstName	VARCHAR(50),
--	lastName	VARCHAR(50),
--	position	VARCHAR(50),
--	salary		INTEGER,
--	phone		VARCHAR(50),
--	date		DATE,
--	email		VARCHAR(50)
--);
--
--CREATE TABLE ITeam (
--	id			INT AUTO_INCREMENT PRIMARY KEY,
--	teamId		INT,
--	name		VARCHAR(50)
--);