package services;

import java.sql.Connection;

import controller.Main;
import dao.CustomerDAO;
import model.Customer;

public class CustomerLogin {
	
	Connection con;
	Cart cart=new Cart(con);
	Main getprod=new Main();
	public CustomerLogin(Connection con)
	{
		this.con=con;
	}
	public void CheckCreds(String userName, String password)
	{
		try
		{
            CustomerDAO customerDAO = new CustomerDAO(con);
            Customer customer = customerDAO.getCustomerByEmail(userName);
            
            if (customer != null && customer.getPassword().equals(password)) {
            	cart.getCustId(customer.getCustomerId());
            	getprod.getCustId(customer.getCustomerId());
                System.out.println("Login successful! Welcome, " + customer.getName());
            } 
            else 
            {
                System.out.println("Login failed. Invalid email or password.");
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
