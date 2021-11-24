package connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
	  public enum IOService{CONSOLE_IO,FILE_IO,DB_IO,REST_IO}
	    
	    public List<EmployeePayrollData> employeePayrollList;
	    
	    private EmployeePayrollDBService employeePayrollDBService;
	    
	    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
	        this.employeePayrollList=employeePayrollList;
	    }
	    public EmployeePayrollService() { 
	    	
	    	
	    	
	    }

	    public long readData(IOService ioService) {
	    	
	        if (ioService.equals(IOService.CONSOLE_IO)) {
	            Scanner scan = new Scanner(System.in);
	            System.out.println("Enter Employee Name");
	            String empName = scan.next();
	            System.out.println("Enter Employee ID");
	            int empID = scan.nextInt();
	            System.out.println("Enter Employee Salary");
	            int empSalary = scan.nextInt();

	            EmployeePayrollData adder = new EmployeePayrollData(empName, empID, empSalary);
	            employeePayrollList.add(adder);
	            long result = employeePayrollList.size();
	            return result;
	            
	        }else if(ioService.equals(IOService.FILE_IO)){
	            this.employeePayrollList = new EmployeePayrollFileIOService().readData();
	            return employeePayrollList.size();
	        }else
	            return 0;
	    }

	    public void empWriteData(IOService ioService) {
	    	
	        if (ioService.equals(IOService.CONSOLE_IO))
	            System.out.println("OutPut\n"+employeePayrollList);
	        else if (ioService.equals(IOService.FILE_IO))
	            new EmployeePayrollFileIOService().writeData(employeePayrollList);
	    }

	    public void printData(IOService ioService) {
	    	
	        if (ioService.equals(IOService.FILE_IO))
	            new EmployeePayrollFileIOService().printData();
	        else
	            System.out.println("Chose File_IO");
	    }

	    public long countEntries(IOService ioService) {
	    	
	        if (ioService.equals(IOService.FILE_IO))
	            return new EmployeePayrollFileIOService().countEntries();
	        return 0;
	    }

	    public static void main(String[] args) {
	    	
	        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
	        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
	        employeePayrollService.empWriteData(IOService.FILE_IO);
	        employeePayrollService.readData(IOService.FILE_IO);

	    }
	        
	    public List<EmployeePayrollData> readEmpPayRollData(IOService ioService) {
	        if (ioService.equals(IOService.DB_IO))
	            this.employeePayrollList = new EmployeePayrollDBService().readData();
	        return this.employeePayrollList;
	    }
	    
	    public void updateEmployeeSalary(String empName, double empSalary) {
	    	
	        int result = employeePayrollDBService.updateEmployeeData(empName,empSalary);
	        if (result == 0) return;
	        EmployeePayrollData employeePayRollData = this.getEmployeePayrollData(empName);
	        if (employeePayRollData != null)
	        	employeePayRollData.employeeSalary= (int) empSalary;
	    }
	    public EmployeePayrollData getEmployeePayrollData(String name) {
	        for (EmployeePayrollData data : employeePayrollList) {
	            if (data.employeeName.equals(name))
	                return data;
	        }
	        return null;
	    }
		public boolean checkEmployeePayRollSyncWithDB(String name) {
			
		       List<EmployeePayrollData>employeePayrollDataList= employeePayrollDBService.getEmployeePayrollData(name);
		        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));

		}    

}
