package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import dao.ProductDAO;
import model.Products;
public class ViewProducts 


{
	Connection con;
	public ViewProducts(Connection con) {
		this.con = con;
	}
	
	
	
	public void AllProducts(String catergoryName)
	{
		try {
            ProductDAO productDAO = new ProductDAO(con);
            List<Products> products = productDAO.getAllProducts(catergoryName);

            if (!products.isEmpty()) {
                System.out.println("List of all products:");
                for (Products product : products) {
                	System.out.println("Product ID: " + product.getProductId());
                    System.out.println("Name: " + product.getProductName());
                    System.out.println("Price: " + product.getProductPrice());
                }
            } 
            else 
            {
                System.out.println("No products found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
	}
}
