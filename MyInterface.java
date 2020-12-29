import java.sql.*;
import java.util.Scanner;
import java.lang.Float;
import java.lang.Integer;

public class MyInterface{
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/cpsc408_2328529?serverTimezone=UTC";

  //  Database credentials
  static final String USER = "jdbcuser";
  static final String PASS = "jdbc";
  public static void main(String[] args) throws SQLException{
    String[] schools = {"UCLA", "UCI", "UCSD", "UCR", "Chapman University", "Pomona College", "Loyala Marymount", "USC", "Saddleback College", "Irvine Community College",
      "University of Utah", "Utah State", "Dixie State", "Southern Utah", "Brigham Young", "Neumont College", "Weber State", "Snow College", "Utah Valley", "Westminister College",
      "Columbia University", "New York University", "Cornell College", "Baruch College", "Fordham University", "Hunter College", "Manhatten College", "Juiliard School", "Vaughn College", "Pratt Institute",
      "University of Texas", "Texas AMU", "University of Houston", "Rice University", "University of North Texas", "Texas State", "Texas Tech", "Baylor University", "Austin College", "Trinity University",
      "University of Georgia", "Georgia State", "Geogia Institute of Tech", "Georgia College", "Emory University", "Albany State", "Mercer University", "Spelman College", "Berry College", "Columbus State",
      "University of Florida", "University of Miami", "Florida College", "University of South Florida", "Flordia State", "Eckerd College", "Barry University", "Rollins College", "Flagler College", "Beacon College", "Valencia College",
      "NDSU", "University of North Dakota", "Bismarck State College", "University of Mary", "Dickinson State", "Sitting Bull College", "Concordia College", "MSUM", "Presentation College", "Northern State",
      "Colorado State", "Colorado College", "University of Colorado Boulder", "MSU Denver", "University of Denver", "Adams State", "Fort Lewis College", "Colorado Mountain College", "Western Colorado University", "Colorado Mesa",
      "Western Washington", "Washington State", "Central Washington", "Bellevue College", "Seattle University", "Gonzaga University", "Whitman College", "Centralia College", "Whitworth University", "Cascadia University",
      "Chaimplain College", "University of Vermont", "Castleton University", "Middlebury College", "Landmark College", "Norwich University", "Goddard University", "Sterling College", "Vermont Technical", "Dartmouth"};
    String[] majors = {"Computer Science", "Chemistry", "Physics", "Game Design", "Data Analytics", "Math", "Software Engineer", "Mechanical Engineer", "Education", "Business", "Animation", "English", "Philosophy", "Graphic Design"};
    Connection conn = null;
    Statement stmt = null;
    boolean isDone = false;
    String sql;
    int yes;
    boolean checker = false;
    int[] ids = new int[100000];
    int uId, pId, num1, num2, lmao;
    Scanner ans = new Scanner(System.in);
    try{
      Class.forName(JDBC_DRIVER);


      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();

      String response;
      while(!isDone){
        System.out.println("Welcome to the database! What do you want to do?");
        System.out.println("i -- Insert new record");
        System.out.println("d -- Delete a record");
        System.out.println("u -- Update a table row");
        System.out.println("q -- Run a query");
        System.out.println("e -- Exit the program");
        response = ans.next();
        response = response.toLowerCase();

        if(response.equals("i")){
          //String sql;
          //insert

          do{
            checker = true;
            System.out.println("Insert Row:");
            System.out.println("Which table will you insert a row to? (students/states/universities/alumni/scholarships/programs/scores)");


            response = ans.next();
            response = response.toLowerCase();
            if(response.equals("students")){
              System.out.println("Enter new ID");
              int studId = ans.nextInt();
              ans.nextLine();
              studId += 250000;
              System.out.println("Enter first name");
              String first = ans.nextLine();
              System.out.println("Enter last name");
              String last = ans.nextLine();
              System.out.println("Enter GPA");
              Float gpa = Float.valueOf(ans.nextLine()).floatValue();
              System.out.println("Enter year (1-4)");
              int year = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter age");
              int age = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter Program ID (0-1399)");
              int programId = ans.nextInt();
              ans.nextLine();
              int uniId = programId / 14;
              String major = majors[programId%14];
              sql = "INSERT INTO students(id, firstName, lastName, gpa, theYear, age, programId, major, universityId) VALUES ("
              + studId + ", \'"+ first + "\', \'"+ last + "\', "+ gpa + ", " + year + ", " + age + ", " + programId + ", \'" + major +"\', "+uniId + ")";
              int insertData = stmt.executeUpdate(sql);
              sql = "SELECT numStudents FROM universities WHERE id = "+uniId;
              ResultSet rs;

              rs = stmt.executeQuery(sql);
              rs.next();
              num1 = rs.getInt("numStudents");

              sql = "SELECT numStudents FROM programs WHERE id = "+programId;
              rs = stmt.executeQuery(sql);
              rs.next();
              num2 = rs.getInt("numStudents");

              num1++;
              num2++;
              sql = "UPDATE universities SET numStudents = " +num1 + " WHERE id = "+uniId;
              lmao = stmt.executeUpdate(sql);
              sql = "UPDATE programs SET numStudents = " +num2 + " WHERE id = "+programId;


            } else if(response.equals("states")){
              System.out.println("Enter new ID");
              int stateId = ans.nextInt();
              stateId += 10;
              ans.nextLine();
              System.out.println("Enter state name");
              String name = ans.nextLine();

              System.out.println("Enter population");
              int pop = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter number of universities");
              int num = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter year founded");
              int year = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter average income");

              int income = ans.nextInt();
              ans.nextLine();

              sql = "INSERT INTO states(id, name, population, numUniversities, yearFounded, avgIncome) VALUES ("
              + stateId + ", \'"+ name + "\', "+ pop + ", "+ num + ", " + year + ", " + income + ")";
              int insertData = stmt.executeUpdate(sql);


            } else if(response.equals("universities")){
              System.out.println("Enter new ID");
              int uniId = ans.nextInt();
              uniId += 100;
              ans.nextLine();
              System.out.println("Enter school name");
              String name = ans.nextLine();

              System.out.println("Enter enrollment size");
              int num = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter state");
              int state = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter TRUE or FALSE for if ivy league");
              String ivy = ans.nextLine();
              if(ivy != "TRUE" && ivy != "FALSE"){
                ivy = "FALSE";
              }


              sql = "INSERT INTO universities(id, name, numStudents,state,isIvyLeague) VALUES ("
              + uniId + ", \'"+ name + "\', "+ num + ", \'"+state + "\', " + ivy + ")";
              int insertData = stmt.executeUpdate(sql);


            } else if(response.equals("alumni")){
              System.out.println("Enter new ID");
              int alumId = ans.nextInt();
              ans.nextLine();
              alumId += 50000;
              System.out.println("Enter first name");
              String first = ans.nextLine();
              System.out.println("Enter last name");
              String last = ans.nextLine();
              System.out.println("Enter University ID (0-1399 unless inserted)");
              int uniId = ans.nextInt();
              ans.nextLine();


              System.out.println("Enter income");
              int income = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter job");
              String job = ans.nextLine();
              sql = "INSERT INTO alumni(id, firstName, lastName, uniId, income, job) VALUES ("
              + alumId + ", \'"+ first+ "\', \'"+ last + "\', "+uniId+ ", " + income + ", \'" + job + "\')";
              int insertData = stmt.executeUpdate(sql);


            } else if(response.equals("scores")){
              System.out.println("Enter ID");
              int id = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter subject");
              String subject = ans.nextLine();
              System.out.println("Enter score");
              int score = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter student ID");
              int studId = ans.nextInt();
              ans.nextLine();
              sql = "INSERT INTO scores(id, subject, score, studentId) VALUES ("
              + id + ", \'" + subject + "\', "+ score + ", "+ studId +")";
              int insertData = stmt.executeUpdate(sql);

            } else if(response.equals("programs")){
              System.out.println("Enter ID");
              int id = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter name");
              String name = ans.nextLine();
              System.out.println("Enter university ID");
              int uniId = ans.nextInt();
              ans.nextLine();
              System.out.println("Enter number of students");
              int num = ans.nextInt();
              ans.nextLine();
              sql = "INSERT INTO programs(id, name, universityId, numStudents, isHonors, isStem) VALUES ("
              + id + ", \'" + name + "\', " + uniId + ", " + num +", FALSE, FALSE)";
              int insertData = stmt.executeUpdate(sql);

            } else {
              checker = false;
            }

          } while(!checker);
          //System.out.println("Executing: " + sql);
          System.out.println("Insertion done!");


        }
        else if(response.equals("d")){
          ResultSet rs;
          //String sql;

          do{
            ans.nextLine();
            yes = 0;
            checker = true;
            String newresponse;
            System.out.println("Delete Rows:");
            System.out.println("What table will you delete rows from?");
            newresponse = ans.nextLine();
            newresponse = newresponse.toLowerCase();
            System.out.println("Give the primary key to kill");
            int key = ans.nextInt();
            ans.nextLine();
            if(newresponse.equals("states")){
              sql = "DELETE FROM states WHERE id = " + key;
              yes = stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");

            } else if(newresponse.equals("students")){
              sql = "DELETE FROM scores WHERE studentId = " + key;
              yes = stmt.executeUpdate(sql);
              sql = "DELETE FROM scholarships WHERE studentId = " +key;
              yes += stmt.executeUpdate(sql);
              //resolve update anomolies
              sql = "SELECT universityId, programId FROM students WHERE id = " +key;

              rs = stmt.executeQuery(sql);
              rs.next();
              uId = rs.getInt("universityId");
              pId = rs.getInt("programId");

              sql = "SELECT numStudents FROM universities WHERE id = "+uId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num1 = rs.getInt("numStudents");

              sql = "SELECT numStudents FROM programs WHERE id = "+pId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num2 = rs.getInt("numStudents");

              num1--;
              num2--;
              sql = "UPDATE universities SET numStudents = " +num1 + " WHERE id = "+uId;
              lmao = stmt.executeUpdate(sql);
              sql = "UPDATE programs SET numStudents = " +num2 + " WHERE id = "+pId;
              //decrement enrollment for program and university the deleted student was in

              lmao = stmt.executeUpdate(sql);


              sql = "DELETE FROM students WHERE id = "+key;
              yes += stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");

            } else if(newresponse.equals("programs")){
              sql = "SELECT id FROM students WHERE programId = " +key;
              rs = stmt.executeQuery(sql);
              int index = 0;
              while(rs.next()){
                ids[index] = rs.getInt("id");
                index++;
              }
              for(int i = 0; i < index; i++){
                sql = "DELETE FROM scores WHERE studentId = " +ids[i];
                yes += stmt.executeUpdate(sql);
                sql = "DELETE FROM scholarships WHERE studentId = " +ids[i];
                yes += stmt.executeUpdate(sql);
              }
              sql = "SELECT universityId, programId FROM students WHERE id = " +key;

              rs = stmt.executeQuery(sql);
              rs.next();
              uId = rs.getInt("universityId");
              pId = rs.getInt("programId");

              sql = "SELECT numStudents FROM universities WHERE id = "+uId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num1 = rs.getInt("numStudents");

              sql = "SELECT numStudents FROM programs WHERE id = "+pId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num2 = rs.getInt("numStudents");

              num1--;
              num2--;
              sql = "UPDATE universities SET numStudents = " +num1 + " WHERE id = "+uId;
              lmao = stmt.executeUpdate(sql);
              sql = "UPDATE programs SET numStudents = " +num2 + " WHERE id = "+pId;

              lmao = stmt.executeUpdate(sql);
              sql = "DELETE FROM students WHERE programId = " +key;
              yes = stmt.executeUpdate(sql);
              sql = "DELETE FROM programs WHERE id = " + key;
              yes += stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");


            } else if(newresponse.equals("universities")){
              sql = "DELETE FROM alumni WHERE uniId = " +key;
              yes = stmt.executeUpdate(sql);
              sql = "SELECT universityId, programId FROM students WHERE id = " +key;

              rs = stmt.executeQuery(sql);
              rs.next();
              uId = rs.getInt("universityId");
              pId = rs.getInt("programId");

              sql = "SELECT numStudents FROM universities WHERE id = "+uId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num1 = rs.getInt("numStudents");

              sql = "SELECT numStudents FROM programs WHERE id = "+pId;
              rs = stmt.executeQuery(sql);
              rs.next();
              num2 = rs.getInt("numStudents");

              num1--;
              num2--;
              sql = "UPDATE universities SET numStudents = " +num1 + " WHERE id = "+uId;
              lmao = stmt.executeUpdate(sql);
              sql = "UPDATE programs SET numStudents = " +num2 + " WHERE id = "+pId;

              lmao = stmt.executeUpdate(sql);
              sql = "SELECT id FROM students WHERE universityId = " +key;
              rs = stmt.executeQuery(sql);
              int index = 0;
              while(rs.next()){
                ids[index] = rs.getInt("id");
                index++;
              }
              for(int i = 0; i < index; i++){
                sql = "DELETE FROM scores WHERE studentId = " +ids[i];
                yes += stmt.executeUpdate(sql);
                sql = "DELETE FROM scholarships WHERE studentId = " +ids[i];
                yes += stmt.executeUpdate(sql);
              }
              sql = "SELECT universityId, programId FROM students WHERE id = " +key;

              rs = stmt.executeQuery(sql);
              rs.next();
              uId = rs.getInt("universityId");
              pId = rs.getInt("programId");

              sql = "SELECT numStudents FROM universities WHERE id = "+uId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num1 = rs.getInt("numStudents");

              sql = "SELECT numStudents FROM programs WHERE id = "+pId;

              rs = stmt.executeQuery(sql);
              rs.next();
              num2 = rs.getInt("numStudents");

              num1--;
              num2--;
              sql = "UPDATE universities SET numStudents = " +num1 + " WHERE id = "+uId;
              lmao = stmt.executeUpdate(sql);
              sql = "UPDATE programs SET numStudents = " +num2 + " WHERE id = "+pId;

              lmao = stmt.executeUpdate(sql);
              sql = "DELETE FROM students WHERE universityId = " + key;
              yes += stmt.executeUpdate(sql);
              sql = "DELETE FROM programs WHERE universityId = " +key;
              yes += stmt.executeUpdate(sql);
              sql = "DELETE FROM universities WHERE id = " +key;
              yes += stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");


            } else if(newresponse.equals("scholarships")){
              sql = "DELETE FROM scholarships WHERE id = " + key;
              yes = stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");

            } else if(newresponse.equals("scores")){
              sql = "DELETE FROM scores WHERE id = " + key;
              yes = stmt.executeUpdate(sql);
              System.out.println(yes + " rows deleted.");

            } else {
              checker = false;
              ans.nextLine();
            }
          } while(!checker);


        } else if(response.equals("u")){
          //update
          System.out.println("Update rows.");
          sql = "UPDATE students SET gpa = 3.0 WHERE gpa < 3";
          int rows = stmt.executeUpdate(sql);
          System.out.println( rows + " rows updated in students table");

        } else if(response.equals("q")){
          System.out.println("Query time! Pick from 1-7");
          int query = ans.nextInt();
          ans.nextLine();
          ResultSet rs;
          switch(query){
            case 1:
              sql = "SELECT s1.name, s2.name FROM universities s1 INNER JOIN states s2 ON s1.state = s2.name AND s2.name = \'California\'";
              rs = stmt.executeQuery(sql);
              while(rs.next()) {
                 System.out.print("State: " + rs.getString("s1.name") + ", ");
                 System.out.print("School: " + rs.getString("s2.name"));
                 System.out.println();
              }
            break;
            case 2:
              sql = "SELECT s.firstName, s.lastName, d.amount FROM students s INNER JOIN scholarships d ON s.id = d.studentId WHERE d.amount > 8000";
              rs = stmt.executeQuery(sql);
              while(rs.next()) {
                 System.out.print("First name: " + rs.getString("s.firstName") + ", ");
                 System.out.print("Last name: " + rs.getString("s.lastName") + ", ");
                 System.out.print("Amount: " + rs.getInt("d.amount"));
                 System.out.println();
              }


            break;
            case 3:
              sql = "SELECT u.name, u.numStudents, AVG(s.gpa) FROM students s  INNER JOIN universities u ON s.universityId = u.id GROUP BY s.universityId";
              rs = stmt.executeQuery(sql);
              while(rs.next()){
                System.out.print("Univerity: " + rs.getString("u.name") + ", ");
                System.out.print("Students: " + rs.getInt("u.numStudents") + ", ");
                System.out.print("GPA: " + rs.getFloat("AVG(s.gpa)"));

                System.out.println();

              }




            break;
            case 4:
              sql = "SELECT major, AVG(gpa) FROM students GROUP BY major";
              rs = stmt.executeQuery(sql);
              while(rs.next()){
                System.out.print("Major: " + rs.getString("major") + ", ");
                System.out.print("GPA: " + rs.getFloat("AVG(gpa)"));
                System.out.println();

              }

            break;
            case 5:
              sql = "SELECT s.name, s.population, SUM(u.numStudents) FROM universities u INNER JOIN states s ON u.state = s.name GROUP BY u.state";
              rs = stmt.executeQuery(sql);
              while(rs.next()){
                System.out.print("School name: " + rs.getString("s.name") + ", ");
                System.out.print("Population: " + rs.getInt("s.population") + ", ");

                System.out.print("Enrollment: " + rs.getInt("SUM(u.numStudents)"));
                System.out.println();

              }


            break;
            case 6:
              sql = "SELECT s.firstName, s.lastName, s2.score FROM students s INNER JOIN scores s2 ON s.id = s2.studentId WHERE s2.score > 90";
              rs = stmt.executeQuery(sql);
              while(rs.next()) {
                 System.out.print("First name: " + rs.getString("s.firstName") + ", ");
                 System.out.print("Last name: " + rs.getString("s.lastName") + ", ");
                 System.out.print("Score: " + rs.getInt("s2.score"));
                 System.out.println();
              }


            break;
            case 7:
              sql = "SELECT s1.name, AVG(s2.income) FROM universities s1 INNER JOIN alumni s2 ON s1.id = s2.uniId";
              rs = stmt.executeQuery(sql);
              while(rs.next()) {
                 System.out.print("School: " + rs.getString("s1.name") + ", ");
                 System.out.print("Average Alumn Income: " + rs.getFloat("AVG(s2.income)"));
                 System.out.println();
              }

            break;
            default:
              System.out.println("No.");
            break;
          }

        } else if(response.equals("e")){
          isDone = true;
        } else {
          System.out.println("That is not a valid choice. Please try again.\n");

        }




      }



    }catch(SQLException se){
       //Handle errors for JDBC
       se.printStackTrace();
    }catch(Exception e){
       //Handle errors for Class.forName
       e.printStackTrace();
    }finally{
       //finally block used to close resources
       try{
          if(stmt!=null)
             stmt.close();
       }catch(SQLException se2){
       }// nothing we can do
       try{
          if(conn!=null)
             conn.close();
       }catch(SQLException se){
          se.printStackTrace();
       }//end finally try
    }//end try

  }
}
