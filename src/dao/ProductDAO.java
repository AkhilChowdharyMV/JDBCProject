package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Products;
import services.Cart;

public class ProductDAO 
{

	private Connection connection;
	
	public ProductDAO(Connection connection) 
	{
        this.connection = connection;
    }
	public List<Products> getAllProducts(String categoryName) throws SQLException
	{
		 List<Products> products = new ArrayList<>();
	        String query = "SELECT * FROM products p where p.category= ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, categoryName);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    Products product = new Products();
	                    product.setProductName(resultSet.getString("prod_name"));
	                    product.setProductPrice(resultSet.getInt("prod_price"));
	                    product.setProductId(resultSet.getInt("prod_id"));
	                    products.add(product);
	                }
	            }
	        }
	        return products;
	}
	public Products getProductById(int productID) throws SQLException {
		Products products=new Products();
		Cart cart=new Cart(connection);
        String query = "SELECT * FROM products p where p.prod_id= ?";
        try (PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, productID);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    Products product = new Products();
                    product.setProductName(resultSet.getString("prod_name"));
                    cart.getPrice(resultSet.getInt("prod_price"));
                    
                }
            }
            
        }
		return products;
		
	}
}
