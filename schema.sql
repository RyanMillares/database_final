CREATE TABLE IF NOT EXISTS states (
  id  integer NOT NULL,
	name text NOT NULL,
  population integer NOT NULL,
  numUniversities integer NOT NULL,
  yearFounded integer NOT NULL,
  avgIncome integer NOT NULL,
  PRIMARY KEY(id)


);
CREATE TABLE IF NOT EXISTS universities (
	id integer NOT NULL,
  name text NOT NULL,
  numStudents integer NOT NULL,
  state text NOT NULL,
  isIvyLeague BOOLEAN NOT NULL,
  PRIMARY KEY(id)


);
CREATE TABLE IF NOT EXISTS programs (
	id integer NOT NULL,
  name text NOT NULL,
  universityId integer NOT NULL,
  numStudents integer NOT NULL,
  isHonors BOOLEAN NOT NULL,
  isStem BOOLEAN NOT NULL,
  FOREIGN KEY (universityId) REFERENCES universities (id),
  PRIMARY KEY(id)


);
CREATE TABLE IF NOT EXISTS students (
	id integer NOT NULL,
	firstName text NOT NULL,
	lastName text NOT NULL,
  gpa real NOT NULL,
  theYear integer NOT NULL,
  age integer NOT NULL,
  programId integer NOT NULL,
  major text NOT NULL,
  universityId integer NOT NULL,
  FOREIGN KEY (programId) REFERENCES programs (id),
  FOREIGN KEY (universityId) REFERENCES universities (id),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS scores (
  id integer NOT NULL,
  subject text NOT NULL,
  score integer NOT NULL,
  studentId integer NOT NULL,
  FOREIGN KEY (studentId) REFERENCES students (id),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS alumni (
  id integer NOT NULL,
  firstName text NOT NULL,
  lastName text NOT NULL,
  uniId integer NOT NULL,
  income integer NOT NULL,
  job text NOT NULL,
  CHECK (income > 0),
  FOREIGN KEY (uniId) REFERENCES universities (id),
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS scholarships (
  id integer NOT NULL,
  name text NOT NULL,
  amount integer NOT NULL,
  studentId integer NOT NULL,
  CHECK (amount > 0),
  FOREIGN KEY (studentId) REFERENCES students (id),
  PRIMARY KEY (id)


);

DELIMITER //
CREATE TRIGGER FixScores
AFTER DELETE ON students
FOR EACH ROW
BEGIN
DELETE s
FROM scores s
WHERE s.studentId = deleted.id;
END

DELIMITER //
CREATE TRIGGER FixScholarships
AFTER DELETE ON students
FOR EACH ROW
BEGIN
DELETE s
FROM scholarships s
WHERE s.studentId = deleted.id;
END

DELIMITER //
CREATE TRIGGER StateCheck
BEFORE INSERT ON states
FOR EACH ROW
BEGIN
IF NEW.avgIncome < 0
THEN
SET NEW.avgIncome = 0;
END IF;
END //
DELIMITER ;
