package entities;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entitities.enums.OrderStatus;

public class Order {
	private Date moment = Date.from(Instant.now());
	private OrderStatus status;
	private Client client;
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(OrderStatus status, Client client, List<OrderItem> items) {
		this.status = status;
		this.client = client;
		this.items = items;
	}

	public String getStatus() {
		return status.toString();
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getMoment() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(this.moment);
	}

	public Client getClient() {
		return client;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem item) {
		this.items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		OrderItem itemToRemove = this.items.stream()
				.filter(x -> x.getProduct().getName() == item.getProduct().getName()).findFirst().orElse(null);
		
		if(itemToRemove != null) this.items.remove(item);
	}
	
	public Double total() {
		Double totalOrder = 0.0;
		
		for(OrderItem item : this.getItems()) {
			totalOrder += item.subTotal();
		}
		
		return totalOrder;
	}
	
	public String toString() {
		StringBuilder orderSummary = new StringBuilder();
		
		orderSummary
			.append("Order moment: ").append(this.getMoment()+"\n")
			.append("Order status: ").append(this.getStatus()+"\n")
			.append("Client: ").append(this.client.toString()+"\n")
			.append("Order items: \n")
		;
		
		Double totalPrice = 0.0;
		
		for(OrderItem item : this.getItems()) {
			orderSummary.append(item.toString() + "\n");
			totalPrice += item.subTotal();
		}
		
		orderSummary.append("Total price: $" + String.format("%.2f", totalPrice));
		
		return orderSummary.toString();
	}
}
