package co.grandcircus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Map<String, Double> itemMenu = new HashMap<>();
		itemMenu.put("Computer", 2399.99);
		itemMenu.put("iPad", 299.99);
		itemMenu.put("Calculator", 11.98);
		itemMenu.put("Television", 568.99);
		itemMenu.put("Radio", 29.99);
		itemMenu.put("Laptop", 899.99);
		itemMenu.put("Printer", 189.99);
		itemMenu.put("Xbox", 289.99);
		ArrayList<String> items = new ArrayList<>(
				Arrays.asList("Computer", "iPad", "Calculator", "Television", "Radio", "Laptop", "Printer", "Xbox"));
		ArrayList<Double> prices = new ArrayList<>(
				Arrays.asList(2399.99, 299.99, 11.98, 568.99, 29.99, 899.99, 189.99, 289.99));
		ArrayList<String> userOrder = new ArrayList<>();

		System.out.println("Welcome to The Electronics Store!\n");
		
		menu(itemMenu);
		String cont = "y";
		String order = "";

		while (cont.equals("y")) {
			System.out.println();
			order = Validator.getString(scan, "What would you like to order? ");
			if (items.contains(order)) {
				userOrder.add(order);
				System.out.println("Adding " + order + " to cart at " + prices.get(items.indexOf(order)));
			} else {
				while (!items.contains(order)) {
					System.out.println("Sorry, we don't have that item.  Please select another item from the list.");
					menu(itemMenu);
					System.out.println();
					order = Validator.getString(scan, "What item would you like to order? ");
				}
				userOrder.add(order);
				System.out.println("Adding " + order + " to cart at " + prices.get(items.indexOf(order)));
			}

			System.out.println("Would you like to order anything else (y/n)");
			cont = scan.nextLine();
		}

		showOrder(userOrder, prices, items);
		System.out.printf("\nAverage price per item in your order was $" + "%.2f", getAverage(userOrder, prices, items));
		scan.close();

	}

	public static void menu(Map<String, Double> menu) {
		for (String item : menu.keySet()) {
			System.out.printf("%-15s $%.2f\n", item, menu.get(item));
		}
	}

	public static void showOrder(ArrayList<String> orders, ArrayList<Double> prices, ArrayList<String> items) {
		System.out.println("\nThank you for your order!\nThis is what you got:\n");
		System.out.printf("%-10s %11s\n", "Item:", "Price:");
		for (String item : orders) {
			System.out.printf("%-15s $%.2f\n", item, prices.get(items.indexOf(item)));
		}
	}

	public static double getAverage(ArrayList<String> orders, ArrayList<Double> prices, ArrayList<String> items) {
		double sum = 0;
		for (String item : orders) {
			sum += prices.get(items.indexOf(item));
		}
		return sum / orders.size();
	}

}
