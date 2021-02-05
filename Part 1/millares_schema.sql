CREATE TABLE IF NOT EXISTS students (
	id integer PRIMARY KEY,
	firstName text NOT NULL,
	lastName text NOT NULL,
  year integer NOT NULL,
  age integer NOT NULL,
  programId integer NOT NULL,
  universityId integer NOT NULL,
  FOREIGN KEY (programId) REFERENCES programs (id),
  FOREIGN KEY (universityId) REFERENCES universities (id)

);

CREATE TABLE IF NOT EXISTS programs (
	id integer PRIMARY KEY,
  name text NOT NULL,
  universityId integer NOT NULL,
  numStudents integer NOT NULL,
  isHonors BOOLEAN NOT NULL,
  isStem BOOLEAN NOT NULL,
  FOREIGN KEY (universityId) REFERENCES universities (id)


);
CREATE TABLE IF NOT EXISTS universities (
	id integer PRIMARY KEY,
  name text NOT NULL,
  numStudents integer NOT NULL,
  stateID integer NOT NULL,
  state text NOT NULL,
  isIvyLeague BOOLEAN NOT NULL,
  FOREIGN KEY (stateID) REFERENCES states (id)


);

CREATE TABLE IF NOT EXISTS states (
  id integer PRIMARY KEY
	name text NOT NULL,
  population integer NOT NULL,
  numUniversities integer NOT NULL,
  yearFounded integer NOT NULL,
  avgIncome integer NOT NULL




);
