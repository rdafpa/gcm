package gcm.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class JdbcConnexion {

	static String url="";
	static String user="";
	static String password="";
	
	static Properties prop;
	
	static Connection myConnection;
	
	static {
	try {
		prop=new Properties();
		prop.load(JdbcConnexion.class.getResourceAsStream("/config.properties"));
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}
	
	
	public static Connection connecter() {
		
		try {
			url=prop.getProperty("url");
	        user=prop.getProperty("username");
	        password=prop.getProperty("password");
	        System.out.println("url="+url+" user="+user+" password="+password);	        
			//Class.forName("oracle.jdbc.driver.OracleDriver");// dans les anciennes versions de pilotes ceci est obligatoire
			myConnection=DriverManager.getConnection(url, user, password);
			if(myConnection!=null)
				System.out.println("Connexion établie");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/*catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		return myConnection;
		
		}
	
	
}
