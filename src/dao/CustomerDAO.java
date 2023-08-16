package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAO 
{
	Connection con;
	public CustomerDAO(Connection con)
	{
		this.con=con;
	}
	public void createCustomer(Customer customer) throws SQLException 
	{
        String query = "INSERT INTO customers (cust_name, cust_ph, cust_email, cust_add, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setLong(2, customer.getPhoneNumber());
            statement.setString(3, customer.getEmail());
            statement.setString(5, customer.getPassword());
            statement.setString(4, customer.getAddress());
            statement.executeUpdate();
            
            System.out.println("Customer registration successful!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
	}
	public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM customers WHERE cust_email = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setName(resultSet.getString("cust_name"));
                    customer.setPhoneNumber(resultSet.getLong("cust_ph"));
                    customer.setEmail(resultSet.getString("cust_email"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setAddress(resultSet.getString("cust_add"));
                    customer.setCustomerId(resultSet.getInt("cust_id"));
                    return customer;
                }
            }
        }
        return null;
    }
}
