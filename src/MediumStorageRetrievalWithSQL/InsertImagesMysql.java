/*
 * The following routine is used to insert a picture file into an SQL database.
 * It does so by retrieving the file from the disk and then placing the contents
 * as a binary stream into the SQL database.
 * By: Kingsley Oteng-Amoako
 * Date: Feburary 8th, 2018
 */

package MediumStorageRetrievalWithSQL;

/**
 *
 * @author Kingsley Oteng-Amoako
 * @date Feburary 8th, 2018
 */
import java.sql.*;
import java.io.*;



public class InsertImagesMysql{
	public static void main(String[] args){
		System.out.println("Insert an image into an SQL database");
                
                // Handle to the driver name
		String driverName = "com.mysql.jdbc.Driver";
                
                // Connect to a standard local hose
		String url = "jdbc:mysql://localhost:3306/";
                
                // **
                // Create the database a standard database connect
                // databaseName: [test]
                // user: [sqluser]
                // user password: [sqluserpw]
                // **
                
		String dbName = "test";
		String userName = "sqluser";
		String password = "sqluserpw";
                
                //** 
                // setup a mysql connection handle 
                // **
		Connection con = null;
                
                // ** 
                // estbalish a connection to the driver
                // then connect to the database
                // create prepared statement
                // finally store the file as a bytestream 
                // as type Blob within the mysql database
                // **
                
		try{
		   Class.forName(driverName);
		   con = DriverManager.getConnection(url+dbName,userName,password);
		   Statement st = con.createStatement();
		   File imgfile = new File("c:/temp/post.jpg");
		  
                   // Create the file input stream //
                   FileInputStream fin = new FileInputStream(imgfile);
		 
                   // Pregenerate the MySQL prepared statement
		   PreparedStatement pre =
		   con.prepareStatement("insert into materials_a values(?,?,?)");
		 
                   // Populate the prepared statement files
                   
                    // store the integer value
                    pre.setInt(1,7);
                    // store the string 
                    pre.setString(2,"test");
                    // store the binary stream
                    pre.setBinaryStream(3,(InputStream)fin,(int)imgfile.length());
                   
                  // Execute the SQL commands
		   pre.executeUpdate();
                   
                   
		   System.out.
                           println("Successfully inserted the file into the database!");

                  // Exit queries and close connection 
		   pre.close();
		   con.close(); 
		}
                    catch (Exception e1)
                {
                    System.out.
                            println(e1.getMessage());
		}
	
                    System.out
                                .println("Media stored within Blob in database");
        }

}


