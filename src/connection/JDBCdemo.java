package connection;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBCdemo {
public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/sumit?useSSL=false";
		String userName ="root";
		String password = "Sumit@123";	
		Connection connection;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("driver is loaded !!");
	} catch ( Exception e) {
		
		e.printStackTrace();
	}
	listDrivers();
	try {
		System.out.println("connecting to database :" +jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("connection is succesfull :" +connection);
		
	} catch ( Exception e) { 
	
		e.printStackTrace();
	}
}
private static void listDrivers() {
	
	Enumeration<Driver> driverlist = DriverManager.getDrivers();
	
	while(driverlist.hasMoreElements()) {
		
		 Driver driverClass = (Driver) driverlist.nextElement();
		 System.out.println(" "+driverClass.getClass().getName());
		}
	}

}
