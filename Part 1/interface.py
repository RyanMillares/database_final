import sqlite3
from sqlite3 import Error
from mysql.connector import MySQLConnection, Error
from python_mysql_dbconfig import read_db_config

def create_connection(db_file):
    """ create a database connection to the SQLite database
        specified by db_file
    :param db_file: database file
    :return: Connection object or None
    """
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)

    return conn

def create_table(conn, create_table_sql):
    """ create a table from the create_table_sql statement
    :param conn: Connection object
    :param create_table_sql: a CREATE TABLE statement
    :return:
    """
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
    except Error as e:
        print(e)


def create_student(conn, student):
    """
    Create a new project into the projects table
    :param conn:
    :param project:
    :return: project id
    """
    sql = ''' INSERT INTO students(name,firstName,lastName,year,age,programId,universityId)
              VALUES(?,?,?,?,?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, project)
    conn.commit()
    return cur.lastrowid


def create_program(conn, program):
    """
    Create a new task
    :param conn:
    :param task:
    :return:
    """

    sql = ''' INSERT INTO programs(name,universityId,numStudents,isHonors,isStem)
              VALUES(?,?,?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, task)
    conn.commit()
    return cur.lastrowid

def create_school(conn, school):
    """
    Create a new task
    :param conn:
    :param task:
    :return:
    """

    sql = ''' INSERT INTO universities(name,numStudents,stateID,state,isIvyLeague)
              VALUES(?,?,?,?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, task)
    conn.commit()
    return cur.lastrowid

def create_state(conn, state):
    """
    Create a new task
    :param conn:
    :param task:
    :return:
    """

    sql = ''' INSERT INTO states(name,population,numUniversitiess,yearFounded, avgIncome)
              VALUES(?,?,?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, task)
    conn.commit()
    return cur.lastrowid

def delete_student(student_id):
    db_config = read_db_config()

    query = "DELETE FROM students WHERE id = %s"

    try:
        # connect to the database server
        conn = MySQLConnection(**db_config)

        # execute the query
        cursor = conn.cursor()
        cursor.execute(query, (student_id,))

        # accept the change
        conn.commit()

    except Error as error:
        print(error)

    finally:
        cursor.close()
        conn.close()

def delete_program(program_id):
    db_config = read_db_config()

    query = "DELETE FROM programs WHERE id = %s"

    try:
        # connect to the database server
        conn = MySQLConnection(**db_config)

        # execute the query
        cursor = conn.cursor()
        cursor.execute(query, (program_id,))

        # accept the change
        conn.commit()

    except Error as error:
        print(error)

    finally:
        cursor.close()
        conn.close()

def delete_school(school_id):
    db_config = read_db_config()

    query = "DELETE FROM universities WHERE id = %s"

    try:
        # connect to the database server
        conn = MySQLConnection(**db_config)

        # execute the query
        cursor = conn.cursor()
        cursor.execute(query, (school_id,))

        # accept the change
        conn.commit()

    except Error as error:
        print(error)

    finally:
        cursor.close()
        conn.close()


def delete_state(state_id):
    db_config = read_db_config()

    query = "DELETE FROM states WHERE id = %s"

    try:
        # connect to the database server
        conn = MySQLConnection(**db_config)

        # execute the query
        cursor = conn.cursor()
        cursor.execute(query, (state_id,))

        # accept the change
        conn.commit()

    except Error as error:
        print(error)

    finally:
        cursor.close()
        conn.close()
def select_all_students(conn):
    """
    Query all rows in the tasks table
    :param conn: the Connection object
    :return:
    """
    cur = conn.cursor()
    cur.execute("SELECT * FROM students")

    rows = cur.fetchall()

    for row in rows:
        print(row)

def custom_query(conn, input):
    if input == 1:
        select_all_students(conn)
    elif input == 2:
        cur = conn.cursor()
        cur.execute("SELECT states.name, AVG(numStudents) FROM universities GROUP BY stateID JOIN states on universities.stateID == states.id")
        rows = cur.fetchall()
        for row in rows:
            print(row)
    elif input == 3:
        cur = conn.cursor()
        cur.execute("SELECT numUniversities, yearFounded FROM states")
        rows = cur.fetchall()
        for row in rows:
            print(row)
    elif input == 4:
        cur = conn.cursor()
        cur.execute("SELECT s1.firstName, s1.lastName, s2.firstName, s2.lastName, s FROM students s1, students s2 WHERE s1.firstName == s2.firstName")
        rows = cur.fetchall()
        for row in rows:
            print(row)
    elif input == 5:
        cur = conn.cursor()
        cur.execute("SELECT name FROM programs JOIN universities ON universityId == id WHERE universities.name == 'Chapman University'")
        rows = cur.fetchall()
        for row in rows:
            print(row)
    else:
        print("Catastrophic failure")


def main():
    database = r"C:\sqlite\db\mydatabase.db"

    sql_create_students_table = """ CREATE TABLE IF NOT EXISTS students (
    	id integer PRIMARY KEY,
    	firstName text NOT NULL,
    	lastName text NOT NULL,
      year integer NOT NULL,
      age integer NOT NULL,
      programId integer NOT NULL,
      universityId integer NOT NULL,
      FOREIGN KEY (programId) REFERENCES programs (id),
      FOREIGN KEY (universityId) REFERENCES universities (id)

    ); """

    sql_create_programs_table = """CREATE TABLE IF NOT EXISTS programs (
    	id integer PRIMARY KEY,
      name text NOT NULL,
      universityId integer NOT NULL,
      numStudents integer NOT NULL,
      isHonors BOOLEAN NOT NULL,
      isStem BOOLEAN NOT NULL,
      FOREIGN KEY (universityId) REFERENCES universities (id)


    );"""

    sql_create_schools_table = """CREATE TABLE IF NOT EXISTS universities (
    	id integer PRIMARY KEY,
      name text NOT NULL,
      numStudents integer NOT NULL,
      stateID integer NOT NULL,
      state text NOT NULL,
      isIvyLeague BOOLEAN NOT NULL,
      FOREIGN KEY (stateID) REFERENCES states (id)


    );"""

    sql_create_states_table = """CREATE TABLE IF NOT EXISTS states (
      id integer PRIMARY KEY
    	name text NOT NULL,
      population integer NOT NULL,
      numUniversities integer NOT NULL,
      yearFounded integer NOT NULL,
      avgIncome integer NOT NULL




    );"""


    # create a database connection
    conn = create_connection(database)

    # create tables
    if conn is not None:
        # create projects table
        create_table(conn, sql_create_students_table)
        create_table(conn, sql_create_programs_table)
        create_table(conn, sql_create_schools_table)
        create_table(conn, sql_create_states_table)
    else:
        print("Error! cannot create the database connection.")

    flag = 1;
    while flag > 0:
        checker = 0
        print("Do you wish to add, delete, query, or exit?")
        value = input("Choice (A, D, Q): ")
        if lower(value) == 'a': #add
            TESTING
            print("In what table are you adding? Students, Programs, Universities, or States?")
            value1 = input("Choose (S: student, P: program, U: university, t: state)")
            if lower(value1) == 's':
                name1 = input("First Name: ")
                name2 = input("Last Name: ")
                year = input("Grade Level (1-4): ")
                age = input("Age: ")
                programId = input("Program ID: ")
                universityId = input("University ID: ")
                newStudent = (name1, name2, year, age, programId, universityId)
                create_student(conn,newStudent)


            elif lower(value1) == 'p':
                name = input("Program name: ")
                university = input("University ID: ")
                num = input("Number of students: ")
                isHon = input("Is it honors? (0/1) : ")
                isStem = input("Is it STEM? (0/1): ")

                newProgram = (name, university, num, isHon, isStem)
                create_program(conn,newProgram)
            elif lower(value1) == 'u':
                name = input("School name: ")
                num = input("Number of enrolled students: ")
                stateId = input("ID of state: ")
                state = input("Name of state: ")
                isIvy = input("Is it Ivy League? (0/1): ")

                newSchool = (name, num, stateId, state, isIvy)
                create_school(conn,newSchool)
            elif lower(value1) == 't':
                name = input("State name: ")
                pop = input("State population: ")
                num = input("Number of universities: ")
                year = input("Year founed: ")
                avg = input("Average income: ")

                newState = (name, pop, num, year, avg)
                create_state(conn, newState)
            else:
                print("catastrophic failure")
                flag = 0


        elif lower(value) == 'd': #delete
            print("From what table are you removing a record?")
            value2 = input("Choose (S: student, P: program, U: university, t: state)")
            if lower(value2) == 's':
                newInput = input("Provide the ID of the student to remove: ")
                delete_student(newInput)
            elif lower(value2) == 'p':
                newInput = input("Provide the ID of the program to remove: ")
                delete_program(newInput)
            elif lower(value2) == 'u':
                newInput = input("Provide the ID of the school to remove: ")
                delete_school(newInput)
            elif lower(value2) == 't':
                newInput = input("Provide the ID of the state to remove: ")
                delete_state(newInput)
            else:
                print("Catastropic failure")
                flag = 0


        elif lower(value) == 'q': #query
            print("The following queries can be used: \n1: SELECT * FROM students\n2: SELECT states.name, AVG(numStudents) FROM universities GROUP BY stateID JOIN states on universities.stateID == states.id")
            print("3: SELECT numUniversities, yearFounded FROM states")
            print("4: SELECT s1.firstName, s1.lastName, s2.firstName, s2.lastName, s FROM students s1, students s2 WHERE s1.firstName == s2.firstName")
            print("5: SELECT name FROM programs JOIN universities ON universityId == id WHERE universities.name == 'Chapman University'")
            value4 = input("Choose (1-5)")
            custom_query(conn, value4)
        elif lower(value) == 'e': #exit
            flag = 0
        else:
            print("That is not a valid input.")
    print("Exitting program...")


if __name__ == '__main__':
    main()
