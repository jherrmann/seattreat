package hackathon.controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.iata.ndc.schema.AirShoppingRQ;
import org.iata.ndc.schema.AirShoppingRS;
import org.iata.ndc.schema.SeatAvailabilityRQ;
import org.iata.ndc.schema.SeatAvailabilityRS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hackathon.domainobjects.Product;
import hackathon.ndc.AirShoppingRQBuilder2;
import hackathon.ndc.NdcClient;
import hackathon.ndc.SeatAvailabilityRQBuilder;

@RestController
public class ProductController {

	private static final String API_KEY = "getYourOwn:)";
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/product")
	public Product product(@RequestParam(value = "name", defaultValue = "World") final String name) {
		iataTest();
		return new Product(counter.incrementAndGet(), String.format(ProductController.template,
				name));
	}

	private void iataTest() {
		SeatAvailabilityRQBuilder builder = new SeatAvailabilityRQBuilder();
		builder.addTravelAgencySender("Test agency", "0000XXXX", "test agent");
		//builder.addOriginDestination("CDG", "LHR", new Date());
		builder.addAirlinePreference("C9");
		SeatAvailabilityRQ request = builder.build();

		NdcClient client = new NdcClient("http://iata.api.mashery.com/kronos/api",
				API_KEY);
		SeatAvailabilityRS response = null;
		try {
			response = client.seatAvailability(request);
			System.out.println("iata call successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
