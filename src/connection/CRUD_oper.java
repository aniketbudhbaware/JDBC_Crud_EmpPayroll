package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD_oper {
	public static void main(String[] args) throws SQLException {
		
		CRUD_oper oper =new CRUD_oper();
		oper.save();

	}

	public boolean save() throws SQLException {
		
			boolean result=false;
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection on=DriverManager.getConnection("jdbc:mysql://localhost:3306/Aniket","root","Aniket@123");
					Statement st=on.createStatement();
					int count =st.executeUpdate("insert into employee_payroll values (1 ,'Aniket','F',390000.00,'2019-09-08')");

					if(count > 0)
					{
						System.out.println("Inserted Successfully");
						result = true;	
					}
					on.close();	

				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
				return result;
	}

		public boolean updateData() {
			
			boolean result = false ;
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Aniket", "root", "Aniket@123");
				Statement st=con.createStatement();
				int count=st.executeUpdate(" update employee_payroll set name ='suraj' where id =1");
				if(count > 0)
				{
					System.out.println("Update successfull");
					return true;
				}
				con.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return result;
		}

		public boolean deleteData() {
			
			boolean result = false  ;
			try {
				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Aniket", "root", "Aniket@123");
			Statement st=con.createStatement();
			int count =st.executeUpdate(" delete from employee_payroll where id = 1");
			if (count > 0) {
				
				System.out.println("delete successfull");
				return true;
			}
			con.close();
			} catch(Exception e) {
				
				e.printStackTrace();
			}
			return result;
		}
		
		public void selectData() {
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Aniket", "root", "Aniket@123");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(" select * from employee_payroll");

				while(rs.next()) {

					System.out.print(rs.getInt("id")+"\t");
					System.out.print(rs.getString("name")+"\t");
					System.out.print(rs.getString("salary")+"\t");
					System.out.println(rs.getString("start"));
				}
				con.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}

}
