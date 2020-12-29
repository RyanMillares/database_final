import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.sql.*;

import java.util.Random;

public class MakeSql {

  public static void main(String[] args) {
    String[] firstNames = {"John", "David", "Michael", "Chris", "Mike", "Mark", "Paul", "Daniel", "James", "Maria", "Amelia", "Ryan", "Noah", "Jane", "Emily", "Sarah", "Anna", "Neethi", "Eric", "Nathan"};
    String[] lastNames = {"Smith", "Jones", "Johnson", "Lee", "Brown", "Williams", "Rodriguez", "Garcia", "Gonzalez", "Lopez", "Linstead", "German", "Astley", "Cena", "Blue", "Red", "Black", "White", "Prate", "Hunter"};
    String[] states = {"California", "Utah", "New York", "Texax", "Georgia", "Florida", "North Dakota", "Colorado", "Washington", "Vermont"};
    int[] pops = {39510000, 3206000, 19450000, 28630000, 10620000, 21480000, 762062, 5759000, 7615000, 623989};
    int[] years = {1850, 1896, 1624, 1845, 1788, 1845, 1889, 1876, 1889, 1791};
    int[] incomes = {111632, 112799, 105571, 98362, 84224, 85581, 90647, 107936, 110680, 95683};
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
    String[] subjects = {"Math", "Chemistry", "History", "Writing", "Physics"};
    //less than  8 = isStem
    //less than  8 = notHonors
    String[] jobs = {"Programmer", "Analyst", "Marketing", "Teacher", "Scientist", "Manager", "Engineer", "Artist"};
    int[] enrollment = new int[100];
    int[] programs = new int[1400];
    for(int i = 0; i < 100; i++){
      enrollment[i] = 0;
    }
    for(int i = 0; i < 1400; i++){
      programs[i] = 0;
    }
    Random rand = new Random();
    int upperGpa = 40;
    int upperFirst = 20;
    int upperLast = 20;

    int testNumber = 500;
    //set to 250000 for submission

    //float newgpa;


    try {
      File myObj = new File("data.sql");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    try {
      FileWriter myWriter = new FileWriter("data.sql");

      //insert states
      for(int i = 0; i < 10; i++){
        myWriter.write("INSERT INTO states(id, name, population, numUniversities, yearFounded, avgIncome) VALUES ("
        + i + ", \'" + states[i] + "\', " + pops[i] + ", " + 10 + ", " + years[i] + ", " + incomes[i] + ");\n");
      }

      int school;
      String isIvy;
      for(int i = 0; i < 100; i++){
        school = i/10;
        if(i == 20 || i == 22){
          isIvy = "TRUE";
        }
        else{
          isIvy = "FALSE";
        } //if the school is columbia or cornell, it is ivyleague

        myWriter.write("INSERT INTO universities(id, name, numStudents, state, isIvyLeague) VALUES ("
        + i + ", \'" + schools[i] + "\', " + enrollment[i] + ", \'" + states[school] + "\'," + isIvy +");\n");

      }
      //insert programs
      int newProgram;
      int newMajor;
      int newUni;
      int newState;
      String stem, honor;
      int first, last, age, year, gpa;
      for(int i = 0; i < 1400; i++){
        newMajor = i%14;
        newUni = i / 14;
        if(newMajor < 8){
          stem = "TRUE";
          honor = "FALSE";
        }
        else{
          stem = "FALSE";
          honor = "TRUE";
        }

        myWriter.write("INSERT INTO programs(id, name, universityId, numStudents, isHonors, isStem) VALUES ("
        + i + ", \'" + majors[newMajor] + "\', " + newUni + ", " + programs[i] +  ", " + honor + ", " + stem +");\n");
      }
      //insert students


      for(int i = 0; i < testNumber; i++){
        newProgram = rand.nextInt(1400);
        newUni = newProgram/14;
        newState = newUni/10;
        newMajor = newProgram%14;
        first = rand.nextInt(20);
        last = rand.nextInt(20);
        age = rand.nextInt(6) + 16;
        year = rand.nextInt(4) + 1;
        gpa = rand.nextInt(40);
        if(gpa < 10){
          gpa += 30;
        } //to avoid gpas below 1.0
        float newgpa = (float)gpa / 10;

        myWriter.write("INSERT INTO students(id, firstName, lastName, gpa, theYear, age, programId, major, universityId) VALUES ("
        + i + ", \'" + firstNames[first] + "\', \'" + lastNames[last] + "\', " + newgpa +  ", " + year + ", " + age + ", " + newProgram + ", \'" + majors[newMajor] + "\', " + newUni +");\n");
        programs[newProgram]++;
        enrollment[newUni]++;


      }
      //insert scores
      int newScore;
      int newSubject;
      int newStudent;
      for(int i = 0; i < testNumber*4; i++){
        newScore = rand.nextInt(101);
        newSubject = rand.nextInt(5);
        if(newScore < 40){
          newScore *= 2;
        }
        newStudent = rand.nextInt(testNumber);
        myWriter.write("INSERT INTO scores(id, subject, score, studentId) VALUES ("
        + i + ", \'" + subjects[newSubject] + "\', " + newScore + ", " + newStudent +");\n");



      }
      //insert alumni
      int newJob;
      int newSal;
      for(int i = 0; i < testNumber/5; i++){
        newJob = rand.nextInt(8);
        newSal = (rand.nextInt(20) + 1) * 5000;
        newUni = rand.nextInt(100);
        first = rand.nextInt(20);
        last = rand.nextInt(20);
        myWriter.write("INSERT INTO alumni(id, firstName, lastName, uniId, income, job) VALUES ("
        + i + ", \'" + firstNames[last] + "\', \'" + lastNames[first] + "\'," + newUni + ", " + newSal + ", \'" + jobs[newJob] + "\');\n");

      }
      //insert scholarships
      int newAmount;
      int randnum;
      int isEssay;
      String essay;
      String newName;
      String groupName;
      for(int i = 0; i < testNumber/10; i++){
        newAmount = (rand.nextInt(20) + 1) * 500;
        isEssay = rand.nextInt(2);
        if(isEssay == 1){
          essay = " Essay ";
        }
        else{
          essay = " ";
        }
        randnum = rand.nextInt(120);
        if(randnum < 20){
          groupName = lastNames[randnum] + essay + "Foundation Scholarship";
        }
        else{
          groupName = schools[randnum - 20] + " Grant";
        }
        newStudent = rand.nextInt(testNumber);
        myWriter.write("INSERT INTO scholarships(id, name, amount, studentId) VALUES ("
        + i + ", \'" + groupName + "\', " + newAmount + ", " + newStudent + ");\n");



      }
      for(int i = 0; i < 100; i++){
        myWriter.write("UPDATE universities SET numStudents = " +enrollment[i] + " WHERE id = " + i + ";\n");
      }
      for(int i = 0; i < 1400; i++){
        myWriter.write("UPDATE programs SET numStudents = " +programs[i] + " WHERE id = " + i + ";\n");
      }










      myWriter.close();
      System.out.println("Successfully wrote to the file.");
      /**
      for(int i = 0; i < 100; i++){
        System.out.println(schools[i] + ": " + enrollment[i]);
      }
      **/
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}
