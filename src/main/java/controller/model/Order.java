package controller.model;


import java.sql.Timestamp;

public class Order {
	
	private int id;
	private String orderId;
	private String status;
	private Timestamp date;
	private int userId;
	
	public Order() {
		super();
	}

	public Order(String orderId, String status, Timestamp date, int userId) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.date = date;
		this.userId = userId;
	}

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
}
