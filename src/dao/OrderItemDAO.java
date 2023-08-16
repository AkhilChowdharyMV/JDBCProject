package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderItem;

public class OrderItemDAO {

	Connection connection;
	public OrderItemDAO(Connection connection)
	{
		this.connection=connection;
	}
	public void createOrderItem(OrderItem orderItem) throws SQLException 
	{
		String query = "INSERT INTO orders (cust_id, prod_id,subtotal) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderItem.getCustromerId());
            statement.setInt(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getSubtotal());
            statement.executeUpdate();
        }
	}

	public List<OrderItem> getMyProducts(int custid) throws SQLException {
		List<OrderItem> orders = new ArrayList<>();
        String query = "SELECT * FROM shoppingcart.orders where cust_id= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, custid);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem order = new OrderItem();
                    order.setCustromerId(resultSet.getInt("cust_id"));
                    order.setSubtotal(resultSet.getInt("subtotal"));
                    order.setProductId(resultSet.getInt("prod_id"));
                    orders.add(order);
                }
            }
        }
        return orders;
	}


}
