package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entitities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the client data: ");
		System.out.println("Name: ");
		String nameClient = scan.nextLine();
		
		System.out.println("Email: ");
		String emailClient = scan.nextLine();
		
		System.out.println("Birth date (DD/MM/YYYY): ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date birthdateClient = sdf.parse(scan.nextLine());
		Client client = new Client(nameClient, emailClient, birthdateClient);
		
		System.out.println("Status: ");
		OrderStatus status = OrderStatus.valueOf(scan.next());
		scan.nextLine();
		
		System.out.println("How many items to this order? ");
		Integer quantityItemsOrder = scan.nextInt();
		List<OrderItem> itemsList = new ArrayList<>();
		
		for(int i=0; i < quantityItemsOrder; i++) {
			scan.nextLine();
			System.out.println("Enter #" + (i+1) + " item data: ");
			System.out.println("Product name: ");
			String productName = scan.nextLine();
			
			System.out.println("Product price: ");
			Double productPrice = scan.nextDouble();
			
			System.out.println("Quantity: ");
			Integer productQuantity = scan.nextInt();

			Product product = new Product(productName, productPrice);
			itemsList.add(new OrderItem(productQuantity, product));
		}
		
		Order order = new Order(status, client, itemsList);
		System.out.println(order.toString());
		
		scan.close();
	}

}
