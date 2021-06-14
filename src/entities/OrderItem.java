package entities;

public class OrderItem {
	private Integer quantity;
	private Double price;
	private Product product;
	
	
	public OrderItem(Integer quantity, Product product) {
		this.quantity = quantity;
		this.price = product.getPrice();
		this.product = product;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Double getPrice() {
		this.price = this.product.getPrice();
		return price;
	}


	public Product getProduct() {
		return product;
	}
	
	public double subTotal() {
		return this.quantity * this.price;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		return str
			.append(this.getProduct().getName() + ", ")
			.append("$ " + String.format("%.2f", this.getPrice()) + ", ")
			.append("Quantity: " + this.getQuantity() + ", ")
			.append("Subtotal: $" + String.format("%.2f", this.subTotal()))
			.toString()
		;
			
	}
}
