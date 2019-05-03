package com.paul.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
	}
	/*
	Possible concurrency issues when farmEngine is running and someone buys or sells an item in the market
	when the farmEngine may be updating the farm the market may also be updating the farm, when both are persisted to data base this will cause issues
	Possible way to solve this may be to put all market request in a hash map with the farmerName as the key,
	and the key being a list of marketRequests but these market request need to be removed once they are fulfilled.
	We can do this check during the farmEngine  procedure.
	*/
}
