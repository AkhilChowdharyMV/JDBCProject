package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import dao.OrderItemDAO;
import dao.ProductDAO;
import model.OrderItem;
import model.Products;

public class Cart
{
	Connection connection;
	 static int customerId;
	 static int price;
	
	public void getCustId(int custId)
	{
		customerId=custId;
	}
	
	public void getPrice(int amount)
	{
		price=amount;
	}
	
	
	public Cart(Connection connection)
	{
		this.connection=connection;
	}
	public void AddToCart() throws SQLException
	{
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
	    System.out.println("Enter product ID to Add item to cart : ");
	    int productID=scan.nextInt();
	   
	    ProductDAO productDAO = new ProductDAO(connection);
        Products product = productDAO.getProductById(productID);
        
		if (product != null) {
			
		    OrderItem orderItem = new OrderItem();
		    orderItem.setCustromerId(customerId);
		    orderItem.setProductId(productID);
		    orderItem.setSubtotal(price); 
		    
		    OrderItemDAO orderItemDAO = new OrderItemDAO(connection);
		    orderItemDAO.createOrderItem(orderItem);

		    System.out.println("Item added to cart successfully!");
		    
		    
		    
		} 
		else
		{
		    System.out.println("Invalid product ID.");
		}

	   
	}
}
