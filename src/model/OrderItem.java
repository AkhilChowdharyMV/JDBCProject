package model;

public class OrderItem
{

	int custromerId;
	int orderId;
	int productId;
	int subtotal;
	
	public OrderItem() 
	{
		
	}
	
	public OrderItem(int custromerId, int orderId, int productId, int subtotal) 
	{
		this.custromerId = custromerId;
		this.orderId = orderId;
		this.productId = productId;
		this.subtotal = subtotal;
	}
	
	public void setCustromerId(int custromerId) {
		this.custromerId = custromerId;
	}
	public int getCustromerId() {
		return custromerId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}


}
