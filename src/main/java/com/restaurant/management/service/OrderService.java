package com.restaurant.management.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.restaurant.management.model.CustomerDaoModel;
import com.restaurant.management.model.OrderDaoModel;
import com.restaurant.management.model.Pair;

@Component
public class OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	private static int id = 1;

	//improvement on retry logic to input  can be added later on for wrong input value 
	public Pair<Integer, List<OrderDaoModel>> getOrders() {
		List<OrderDaoModel> orders = new ArrayList<>();
		int deliveryPersonCount = 0;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter Input Line (N,M):");
			String inputLine = scanner.nextLine();

			String[] parts = inputLine.split(",");
			if (parts.length == 2) {
				int N = Integer.parseInt(parts[0].trim());
				int M = Integer.parseInt(parts[1].trim());
				deliveryPersonCount = M;
				for (int i = 0; i < N; i++) {
					String orderLine = scanner.nextLine();
					String[] orderParts = orderLine.split(",");
					int orderPlacementTime = Integer.parseInt(orderParts[0].trim());
					int travelTime = Integer.parseInt(orderParts[1].trim());
					orders.add(new OrderDaoModel(CustomerDaoModel.ID_PREFIX + String.valueOf(id), String.valueOf(id++),
							orderPlacementTime, travelTime));
				}
			} else {
				System.out.println("Invalid input format. Please enter in the format 'N,M'.");
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			LOGGER.error("Exception occurred while reading input : {} ", sw.toString());
		}

		return new Pair<Integer, List<OrderDaoModel>>(deliveryPersonCount, orders);
	}
}
