package pl.edu.agh.iisg.to2.model;

//Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
     
private Connection connect = null;
private Statement statement = null;
private ResultSet resultSet = null;

final private String host = "localhost";
final private String user = "root";
final private String passwd = "";

public void connect() throws ClassNotFoundException, SQLException {
	// This will load the MySQL driver, each DB has its own driver
	   Class.forName("com.mysql.jdbc.Driver");
	   
	   // Setup the connection with the DB
	   connect = DriverManager
	       .getConnection("jdbc:mysql://" + host + "/TOProjects?"
	           + "user=" + user + "&password=" + passwd );
}

public void readDataBase() throws Exception {
 try {
   connect();

   // Statements allow to issue SQL queries to the database
   statement = connect.createStatement();
   // Result set get the result of the SQL query
   resultSet = statement
       .executeQuery("select * from IProject");
   writeResultSet(resultSet);

//   // PreparedStatements can use variables and are more efficient

//
//   preparedStatement = connect
//       .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//   resultSet = preparedStatement.executeQuery();
//   writeResultSet(resultSet);
//
//   // Remove again the insert comment
//   preparedStatement = connect
//   .prepareStatement("delete from feedback.comments where myuser= ? ; ");
//   preparedStatement.setString(1, "Test");
//   preparedStatement.executeUpdate();
//   
//   resultSet = statement
//   .executeQuery("select * from feedback.comments");
//   writeMetaData(resultSet);
//   
 } catch (Exception e) {
   throw e;
 } finally {
   close();
 }

}


public void insertProject(IProject project) throws SQLException, ClassNotFoundException
{
	connect();
	
	System.out.println("Tworz statement");
	System.out.println(project.getId());
	System.out.println(project.getDeadline());
	System.out.println(project.getStartdate());
	System.out.println(project.getBudget());
	
	String command = "INSERT INTO TOProjects.IProject (projectId, deadline, startDate, budget) VALUES "
			+ "(\"" + project.getId() + "\", "
			+ "\"" + project.getDeadline().getValue() + "\", "
			+ "\"" + project.getStartdate().getValue() + "\", "
			+ project.getBudget().getValue() + ")";
	
	System.out.println(command);
	
	PreparedStatement preparedStatement = connect.prepareStatement(command);
	
	System.out.println("Statement: " + preparedStatement);
	preparedStatement.executeUpdate();
	System.out.println("did execute");
	
	close();
}


private void writeMetaData(ResultSet resultSet) throws SQLException {
 //   Now get some metadata from the database
 // Result set get the result of the SQL query
 
 System.out.println("The columns in the table are: ");
 
 System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
 for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
   System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
 }
}

private void writeResultSet(ResultSet resultSet) throws SQLException {
 // ResultSet is initially before the first data set
 while (resultSet.next()) {
   // It is possible to get the columns via name
   // also possible to get the columns via the column number
   // which starts at 1
   // e.g. resultSet.getSTring(2);
   Date startDate = resultSet.getDate("startDate");
   Date deadline = resultSet.getDate("deadline");
   int budget = resultSet.getInt("budget");
   
   System.out.println("Start Date: " + startDate);
   System.out.println("Deadline: " + deadline);
   System.out.println("Budget: " + budget);
 }
}

// You need to close the resultSet
private void close() {
 try {
   if (resultSet != null) {
     resultSet.close();
   }

   if (statement != null) {
     statement.close();
   }

   if (connect != null) {
     connect.close();
   }
 } catch (Exception e) {

 }
}

}
