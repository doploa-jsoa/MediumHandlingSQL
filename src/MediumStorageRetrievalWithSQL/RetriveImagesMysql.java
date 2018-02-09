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
 * @date Feburary 9th, 2018
 */
import java.sql.*;
import java.io.*;

public class RetriveImagesMysql{
    
    private                         String fileAddress;
    private                         String jdbcAddressName;
    private                         String hostNetworkAddressName;
    private                         String databaseName;
    private                         String tableaName;
    
    private                         String userNameLocator;
    private                         String userAuthentication;
    
    private                         int id_as_number;
    
    
    RetriveImagesMysql()
        {
        this.fileAddress = null;
        this.jdbcAddressName = null;
        this.hostNetworkAddressName = null;
        this.databaseName = null;
        this.tableaName = null;
        this.userNameLocator = null;
        this.userAuthentication = null;
        this.id_as_number = 0;
        };
    
    // ** 
    // Media Handling
    // **
    private  void setMedia(String _mediaAddress) {
        this.fileAddress = _mediaAddress;
    };
    
    /**
     *
     * @return String::fileAddress
     */
    private String getMedia(){
        return this.fileAddress;
    };
    
    // ** 
    // SQL JDBC Database name mapping 
    // **
    private  
        void setDatabaseName(String _dbName) {
        this.databaseName = _dbName;
    };
    
    /**
     *
     * @return String::Database name map
     */
    private 
        String getDatabaseName(){
        return this.databaseName;
    };
    
    // ** 
    // JDBC address mapping
    // **
    private  void setJDBCDriverAddress(String _driverAddressName) {
        this.jdbcAddressName = _driverAddressName;
    };
    
    /**
     *
     * @return String::JDBC Address
     */
    private String getJDBCDriverAddress(){
        return this.jdbcAddressName;
    };
    
    // ** 
    // Host address mapping 
    // **
    private  void setHostNetworkAddress(String _hostNetworkAddressName) {
        this.hostNetworkAddressName = _hostNetworkAddressName;
    };
    
    /**
     *
     * @return String::Host address map
     */
    private String getHostNetworkAddress(){
        return this.hostNetworkAddressName;
    };
        
        // ** 
    // User authentication
    // **
    private  
        void setUserAuthenticator(String _dbUserAuthenticator) {
        this.userAuthentication = _dbUserAuthenticator;
    };
    
    /**
     *
     * @return String::User authentication
     */
    private 
        String getUserAuthenticator(){
        return this.userAuthentication;
    };
        
    // ** 
    // User name handle
    // **
    private  
        void setUserNameLocator(String _dbUserNameLocator) {
        this.userNameLocator = _dbUserNameLocator;
    };
    
        // ** 
    // SQL JDBC Database name mapping 
    // **
    private  
        void setTableName(String _dbTableName) {
        this.tableaName = _dbTableName;
    };
    
    /**
     *
     * @return String::Database name map
     */
    private 
        String getTableName(){
        return this.tableaName;
    };
    /**
     *
     * @return String::User name
     */
    private 
        String getUserNameLocator(){
        return this.userNameLocator;
    };
   
    private  
        void setTableUniqueID(int _dbIDNumber) {
        this.id_as_number = _dbIDNumber;
    };
    
    /**
     *
     * @return Integer::SQL table description
     */
    private 
        int getTableUniqueID(){
        return this.id_as_number;
    };
        
	public static void main(String[] args){
                RetriveImagesMysql RM = new RetriveImagesMysql();
                
		System.out
                        .println("Retrive an image from an SQL database");
                
                // Handle to the driver name
		// For example jdbcAddressName = "com.mysql.jdbc.Driver";
                RM.setJDBCDriverAddress("com.mysql.jdbc.Driver");
                
                // Connect to a standard local hose
                // For example hostNetworkAddressName= "jdbc:mysql://localhost:3306/";
                RM.setHostNetworkAddress("jdbc:mysql://localhost:3306/");

                
                // **
                // Create the database a standard database connect
                // databaseName: [test]
                // user: [sqluser]
                // user password: [sqluserpw]
                // **
                
                // databaseName = "test";
                RM.setDatabaseName("test");
                
		//userNameLocator = "sqluser";
		RM.setUserNameLocator("sqluser");
                
                // userAuthentication = "sqluserpw";
                RM.setUserAuthenticator( "sqluserpw");
                
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
			Class.forName(RM.getJDBCDriverAddress());
			con = DriverManager.getConnection(RM.getHostNetworkAddress()+RM.getDatabaseName(),RM.getUserNameLocator(),RM.getUserAuthenticator());
			Statement stmt = con.createStatement();
                        
                        // Set table name within the data
                        RM.setTableName("materials_a");
                        
                        // Set the key field value
                        RM.setTableUniqueID(7);
                        
                        // Construct SQL query
			ResultSet rs = stmt.executeQuery("select MYBLOB from "+RM.getTableName()+" WHERE id = "+RM.getTableUniqueID());
			
                        int i = 0;
                        int c;
                        
                        // store the file as a jpeg to the local drive
			while (rs.next()) {
                           
                                // read and write the output binary stream
				InputStream in = rs.getBinaryStream(1);
                                
                                // set the output file.name
                                RM.setMedia("c:/temp/file1_output_myblob");
				OutputStream f = new FileOutputStream(new File(RM.getMedia()+i+".jpg"));
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
                        catch(IOException | ClassNotFoundException | SQLException ex)
                {
			System.out
                                .println(ex.getMessage());
		}
                
                        System.out
                                .println("Media retrieved from Blob in database");
                                    
	}
}

