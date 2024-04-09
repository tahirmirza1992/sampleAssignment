package com.restaurant.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.restaurant.management.model.DeliveryBoyDaoModel;
import com.restaurant.management.model.OrderDaoModel;
import com.restaurant.management.model.Pair;
import com.restaurant.management.model.Status;

@Component
public class DeliveryPartnerProcessor {

	public List<Pair<String, String>> getAssignedDeliveryPartners(List<DeliveryBoyDaoModel> deliveryBoys,
			List<OrderDaoModel> orders) {
		List<Pair<String, String>> batchResult = new ArrayList<>();
		for (OrderDaoModel order : orders) {
			int totalTime = order.getPlacedOrderTime() + order.getJourneyTime();

			Optional<DeliveryBoyDaoModel> availablePerson = deliveryBoys.stream()
					.filter(d -> d.getCurrentTaskEndTime() <= order.getPlacedOrderTime()).findFirst();

			if (availablePerson.isPresent()) {
				DeliveryBoyDaoModel deliveryBoy = availablePerson.get();
				deliveryBoy.setStatus(Status.BUSY);
				deliveryBoy.setCurrentTaskEndTime(totalTime);
				batchResult.add(new Pair<>(order.getCustomerId(), deliveryBoy.getId()));
			} else {
				batchResult.add(new Pair<>(order.getCustomerId(), "No Food :-("));
			}
		}
		return batchResult;
	}

}