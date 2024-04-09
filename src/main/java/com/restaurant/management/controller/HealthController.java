package com.restaurant.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//health check API required by kubernetes to keep checking pod status once deployed on environment
@RestController
public class HealthController {
	private static Logger LOGGER = LoggerFactory.getLogger(HealthController.class);
	
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public ResponseEntity<String> healthCheck() {
		LOGGER.info("Health API requested");
		return ResponseEntity.status(200).body("{ \"status\": \"Running\"}");
	}
}