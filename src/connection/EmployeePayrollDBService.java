package connection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
	  public PreparedStatement employeePayRollDataStatement;

		public List<EmployeePayrollData> readData() {
	    	
	        String sql = "select * from employee_payroll;";
	        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
	        try (Connection connection = this.getConnection()){
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            while (resultSet.next()){
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                String gender = resultSet.getString("gender");
	                double salary = resultSet.getDouble("salary");
	                LocalDate date = resultSet.getDate("start").toLocalDate();
	                employeePayrollDataList.add(new EmployeePayrollData(name,id,salary,gender,date));
	            }
	            
	        } catch (SQLException throwables) {
	        	
	            throwables.printStackTrace();
	        }
	        return employeePayrollDataList;
	    }

	    private Connection getConnection() throws SQLException {
	    	
	        String jdbcULR = "jdbc:mysql://localhost:3306/mrunal?useSSL=false";
	        String userName = "root";
	        String password = "Mrunal@08";
	        Connection connection;
	        System.out.println("Connecting To DB: " + jdbcULR);
	        connection = DriverManager.getConnection(jdbcULR,userName,password);
	        System.out.println("Connection is successful..! " + connection);
	        return connection;
	    }

		
		
		public int updateEmployeeData(String empName, double empSalary) {
			   
		       String sql =String.format("Update employee_payroll set salary = %.2f where name = '%s';",empSalary,empName);
		       try (Connection connection = this.getConnection()) {
		    	   
		            Statement statement = connection.createStatement();
		            return statement.executeUpdate(sql);
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		       return 0;
		}

		public List<EmployeePayrollData> getEmployeePayrollData(String resultSet2) {
			
			List<EmployeePayrollData>employeePayrollDataList = null;
	        if (this.employeePayRollDataStatement==null)
	            this.preparedStatementForEmployeeData();
	        try {
	            //PreparedStatement employeePayRollDataStatement;
				employeePayRollDataStatement.setString(1,resultSet2);
	            ResultSet resultSet = employeePayRollDataStatement.executeQuery();
	            employeePayrollDataList = this.getEmployeePayrollData(resultSet);
	        	
	        } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            return employeePayrollDataList;
	        }

		private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
			//private List<EmployeePayRollData> getEmployeePayRollData(ResultSet resultSet) {
		        List<EmployeePayrollData>employeePayRollDataList = new ArrayList<>();
		        try {
		            while (resultSet.next()){
		                int id = resultSet.getInt("id");
		                String name = resultSet.getString("name");
		                String gender = resultSet.getString("gender");
		                double salary = resultSet.getDouble("salary");
		                LocalDate date = resultSet.getDate("start").toLocalDate();
		                employeePayRollDataList.add(new EmployeePayrollData(name,id,salary,gender,date));
		            }
		        }catch (SQLException e){
		            e.printStackTrace();
		        }
		        return employeePayRollDataList;
		    }
		

		private void preparedStatementForEmployeeData() {
			// TODO Auto-generated method stub
			 try {
		            Connection connection = this.getConnection();
		            String sql = "select * from employee_payroll where name= ?;";
		            employeePayRollDataStatement = connection.prepareStatement(sql);
		        }catch (SQLException e){
		            e.printStackTrace();
		        }
			
		}

}
