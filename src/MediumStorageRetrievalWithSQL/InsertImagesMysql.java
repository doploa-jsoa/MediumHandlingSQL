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
 * @date Feburary 9th, 2018
 */
import java.sql.*;
import java.io.*;



public class InsertImagesMysql{
    
    private                         String fileAddress;
    private                         String jdbcAddressName;
    private                         String hostNetworkAddressName;
    private                         String databaseName;
    private                         String tableaName;
    
    private                         String userNameLocator;
    private                         String userAuthentication;
    
    private                         String tableDescriptor;
    private                         int id_as_number;
    

    // constructor 
    private InsertImagesMysql() {
        this.fileAddress = null;
        this.jdbcAddressName = null;
        this.hostNetworkAddressName = null;
        this.databaseName = null;
        this.tableaName = null;
        this.userNameLocator = null;
        this.userAuthentication = null;
        this.tableDescriptor = null;
        this.id_as_number = 0;
        
    }
   
    
    // ** 
    // Table Information
    // Our example assumes table format [ID][Descripton]{Medium]
    // **
    private  
        void setTableDescriptor(String _dbTableDesc) {
        this.tableDescriptor = _dbTableDesc;
    };
    
    /**
     *
     * @return String::SQL table description
     */
    private 
        String getTableDescriptor(){
        return this.tableDescriptor;
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
    
    /**
     *
     * @return String::User name
     */
    private 
        String getUserNameLocator(){
        return this.userNameLocator;
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
    

        
    @SuppressWarnings("empty-statement")
	public static void main(String[] args)
            {
                // object declaration
                InsertImagesMysql IM;
                IM = new InsertImagesMysql();
                
                // set file name
                IM.setMedia("_handle_post.jpg");
                
                
            System.out
                    .println("Insert the image into an SQL database");
                
                // Handle to the driver name
                // For example "com.mysql.jdbc.Driver";
                IM.setJDBCDriverAddress("com.mysql.jdbc.Driver");
		
                
                // Now connect to a standard local hot
		// For example url = "jdbc:mysql://localhost:3306/";
                IM.setHostNetworkAddress("jdbc:mysql://localhost:3306/");
                
                // **
                // Create the database a standard database connect
                // databaseName: [test]
                // user: [sqluser]
                // user password: [sqluserpw]
                // **
                
                // map to the sql table
                IM.setDatabaseName("test");
                
                // authenticate user
                IM.setUserNameLocator("sqluser");
                IM.setUserAuthenticator("sqluserpw");
                
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
                
		
                    
                   
		 
                        // Pregenerate the MySQL prepared statement
                        try 
                            {
                                // create connection
                                Class.forName(IM.getJDBCDriverAddress());
		   
                                // connect to the table on host server
                                con = DriverManager.getConnection(IM.getHostNetworkAddress()+IM.getDatabaseName(),IM.getUserNameLocator(),IM.getUserAuthenticator());
		   
                                Statement st = con.createStatement();
                                File imgfile = new File("c:/temp/"+IM.getMedia());
		  
                                // Create the file input stream //
                                IM.setTableName("materials_a");
                                FileInputStream fin = new FileInputStream(imgfile);
                                
                                PreparedStatement pre =
                                con.prepareStatement("insert into "+IM.getTableName()+" values(?,?,?)");
                                
                                // Populate the prepared statement files
                                IM.setTableUniqueID(11);
                                IM.setTableDescriptor("stored0");
                   
                                // store the integer value
                                // store the string 
                                // store the binary stream
                                pre.setInt(1,IM.getTableUniqueID());
                                pre.setString(2,IM.getTableDescriptor());
                                pre.setBinaryStream(3,(InputStream)fin,(int)imgfile.length());
                   
                                // Execute the SQL commands
                                pre.executeUpdate();
                                
                                System.out.
                                         println("Successfully inserted the file into the database!");

                                // Exit queries and close connection 
                                pre.close();
                                con.close(); 
                            }
                        catch (FileNotFoundException | ClassNotFoundException | SQLException e1)
                            {
                                System.out
                                        .println(e1);
                            };                  
	
                    System.out
                                .println("Media stored as Blob in database");
            }

}


