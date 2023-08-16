package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import controller.Main;
import dao.OrderItemDAO;
import model.OrderItem;

public class GetCustomerProducts 
{
	Connection con;
	static int customerId;
	
	public void getCustId(int custId)
	{
		customerId=custId;
	}
	
	public GetCustomerProducts(Connection con)
	{
		this.con = con;
	}

	public void MyProducts(int custID) throws SQLException
	{
		OrderItemDAO orderDAO = new OrderItemDAO(con);
		List<OrderItem> order = orderDAO.getMyProducts(custID);
		
		Main price=new Main();
		
		int totalPrice=0;
		if (!order.isEmpty()) {
		    System.out.println("List of all products:");
		    for (OrderItem orders: order) {
		    	System.out.println("Product ID: " + orders.getProductId());
		        System.out.println("Price: " + orders.getSubtotal());
		    }
		    
		    for(OrderItem orders : order)
			{
				totalPrice += orders.getSubtotal();
			}
		    Main.getTotalPrice(totalPrice);
		}
		else 
		{
		    System.out.println("No products found.");
		}
    
	}
}
