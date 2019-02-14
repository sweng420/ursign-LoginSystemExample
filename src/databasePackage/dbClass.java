package databasePackage;

import java.sql.*;


public class dbClass {

	//Database connection variable
	private Connection conn = null;
	
	
	//Set and try connection to database
	public boolean setupConnetion(){
		try{
			//Setup database connection using UcanAccess driver
			conn = DriverManager.getConnection("jdbc:ucanaccess://M:/MyWork/Year3/SWENG/AccountsDB.accdb");
		
		//If try fails, return false and print error msg
		}catch(Exception ex){
			System.err.println("Expection:  ");
			System.err.println(ex.getMessage());
			return false;
		}
		//If try and connection successful return true
		return true;
	}
	
	//Close connection to database
	public boolean closeConnection(){
		
		try {
			conn.close();
		}catch(Exception ex){
			System.err.println("Expection:  ");
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	//Check if a connection to the database has been made
	public void checkConnection(){
		if (conn == null){
			setupConnetion();
		}
	}
	
	//Check if users entered username and password appear in database
	public boolean checkLoginDetails(String username, String password){
		
		//Checks for database connection
		checkConnection();
		
		try{
			//Setup select query, getting all usernames and passwords 
			//ID field only used for testing
			String query = "SELECT [ID], [Username], [Password] FROM [AccountDatabase]";
			PreparedStatement findUser = conn.prepareStatement(query);
			ResultSet rs = findUser.executeQuery();
			
			//Loop through all records in result set
			while (rs.next()){
				String usernameDB = rs.getString(2);
				String passwordDB = rs.getString(3);
				//Print statements to test for successful database read
				//int currentID = rs.getInt(1);
				//System.out.println("results user:"+usernameDB+ "\n"+"pass: "+passwordDB+"\n"+"ID: "+currentID);
				
				//If username and password exists in database return true
				if(username.equals(usernameDB) && password.equals(passwordDB)){
					return true;
				}	
			}
		//If try query fails, return false and print error msg
		}catch(Exception ex){
			System.err.println("Expection:  ");
			System.err.println(ex.getMessage());
			return false;
		}
		//Returns false if try was successful but username and password are not in database
		return false;
	
	}
	
	//Check for existing username in database to avoid duplicates when registering new users
	public boolean checkExsistingUsername (String newUsername){
		
		//Checks for database connection
		checkConnection();
		
		try {
			//Selects all usernames from database
			String query = "SELECT [Username] FROM [AccountDatabase]";
			PreparedStatement checkUsernames = conn.prepareStatement(query);
			ResultSet rs = checkUsernames.executeQuery();
			
			//Loop through all records in result set
			while (rs.next()){
				String usernameDB = rs.getString(1);
				//If username already exists in database, return true
				if (newUsername.equals(usernameDB)){
					return true;
				}
			}
		//If try fails, return false and print error msg
		}catch(Exception ex){
			System.err.println("Expection:  ");
			System.err.println(ex.getMessage());
			return false;
		}
		return false;
	}
	
	//Adding new record to database for new user accounts
	public boolean addNewAccount(String newUsername, String newPassword){
		
		//Checks for database connection
		checkConnection();
		
		try{
			//Insert new account using text entered by user
			String query = "INSERT INTO AccountDatabase(Username, Password) VALUES(?,?)";
			PreparedStatement addNewAccountDetails = conn.prepareStatement(query);
			addNewAccountDetails.setString(1, newUsername);
			addNewAccountDetails.setString(2, newPassword);
			addNewAccountDetails.executeUpdate();
			//Print statement for testing and confirmation of user being added
			System.out.println("added new user");
			addNewAccountDetails.close();
			//If try successful, and account added, return true
			return true;
			
		//If try unsuccessful, return false and print error msg
		}catch(Exception ex){
			System.err.println("Expection:  ");
			System.err.println(ex.getMessage());
			return false;
		}
		
	}
	
	
}
