package com.restaurant.management;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.restaurant.management.model.DeliveryBoyDaoModel;
import com.restaurant.management.model.OrderDaoModel;
import com.restaurant.management.model.Pair;
import com.restaurant.management.model.Status;
import com.restaurant.management.service.DeliveryPartnerProcessor;
import com.restaurant.management.service.OrderService;

@SpringBootApplication
public class DeliveryManagerApplication implements CommandLineRunner {
	public static final Logger LOGGER = LoggerFactory.getLogger(DeliveryManagerApplication.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private DeliveryPartnerProcessor processor;

	public static void main(String[] args) {
		SpringApplication.run(DeliveryManagerApplication.class, args);
	}

	// spawing a springboot application to have a health controller which will be
	// health API can be used in kubernetes pod instance health check.
	private void printDeliveryReport(List<Pair<String, String>> assignedDeliveryPartners) {
		if (assignedDeliveryPartners != null && !assignedDeliveryPartners.isEmpty()) {
			StringBuffer outputBuffer = new StringBuffer();
			for (Pair<String, String> deliveryDetail : assignedDeliveryPartners) {
				outputBuffer.append(deliveryDetail.getKey() + "-" + deliveryDetail.getValue() + "\n");
			}

			// LOGGER.info("{}", outputBuffer);
			System.out.println("OUTPUT");
			System.out.println(outputBuffer);
		}
	}

	private List<DeliveryBoyDaoModel> intializeDeliveryBoys(Pair<Integer, List<OrderDaoModel>> inputPair) {
		List<DeliveryBoyDaoModel> availableDeliveryBoys = IntStream.rangeClosed(1, inputPair.getKey())
				.mapToObj(number -> {
					DeliveryBoyDaoModel deliveryBoy = new DeliveryBoyDaoModel();
					deliveryBoy.setStatus(Status.NOT_ASSIGNED);
					deliveryBoy.setId(DeliveryBoyDaoModel.ID_PREFIX + number);
					return deliveryBoy;
				}).collect(Collectors.toList());
		return availableDeliveryBoys;
	}

	@Override
	public void run(String... args) throws Exception {
		Pair<Integer, List<OrderDaoModel>> inputPair = null;
	
		LOGGER.info("Input gathering started");
		inputPair = orderService.getOrders();
		
		// assumed the input for delivery boy count is non zero number.
		if (inputPair != null) {
			List<DeliveryBoyDaoModel> availableDeliveryBoys = intializeDeliveryBoys(inputPair);
			List<Pair<String, String>> assignedDeliveryPartners = processor
					.getAssignedDeliveryPartners(availableDeliveryBoys, inputPair.getValue());

			printDeliveryReport(assignedDeliveryPartners);
		}
	}

}
