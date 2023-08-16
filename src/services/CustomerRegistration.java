package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import dao.CustomerDAO;
import model.Customer;

public class CustomerRegistration 
{
	Connection con;
	
	public CustomerRegistration(Connection con)
	{
		this.con=con;
		
	}
	
	public void save() throws SQLException
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
	    System.out.print("Enter first name: ");
	    String firstName = scanner.next();
	    System.out.print("Enter phone number: ");
	    long phoneNumber = scanner.nextLong();
	    System.out.print("Enter email: ");
	    String email = scanner.next();
	    System.out.print("Enter password: ");
	    String password = scanner.next();
	    System.out.print("Enter address: ");
	    String address = scanner.next();
	    
	    Customer newCustomer=new Customer();
	    
	    newCustomer.setName(firstName);
	    newCustomer.setPhoneNumber(phoneNumber);
	    newCustomer.setEmail(email);
	    newCustomer.setPassword(password);
	    newCustomer.setAddress(address);
	    
	    CustomerDAO customerDAO = new CustomerDAO(con);
        customerDAO.createCustomer(newCustomer);
	    
	}
}
