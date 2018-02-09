/*
 * The following routine is used to retrieve data from  an SQL database.
 * It does so by retrieving the file from the disk and then placing the contents
 * as a binary file into the disk.
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

public class RetriveImagesMysql{
	public static void main(String[] args){
		System.out.println("Retrive an image from an SQL database");
                
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
                // finally retrieve the file as a bytestream 
                // from the column Blob within the mysql database
                // **
                
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select MYBLOB from materials_a WHERE id = 7");
			
                        int i = 0;
                        int c;
                        
                        // store the file as a jpeg to the local drive
			while (rs.next()) {
                           
                                // read and write the output binary stream
				InputStream in = rs.getBinaryStream(1);
				OutputStream f = new FileOutputStream(new File("test11"+i+".jpg"));
				i++;
				c = 0;
                                
                                // write all files in the column to local drive
				while ((c = in.read()) > -1) {
					f.write(c);
				}
                                
                                
			// Exit queries and close connection 
                         f.close();
			 in.close();
			}
		}
                        catch(Exception ex)
                {
			System.out
                                .println(ex.getMessage());
		}
                
                        System.out
                                .println("Media retrieved from Blob in database");
                                    
	}
}

