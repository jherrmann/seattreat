package hackathon.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.iata.ndc.schema.SeatAvailabilityRS;
import org.iata.ndc.schema.SeatMapRowNbrType;
import org.junit.Test;

public class ApacheHttpClientTest {

	@Test
	public void test() throws IOException, URISyntaxException{
		
		String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("seatAvailabilityRQ").toURI())));
		SeatAvailabilityRS seatAvailability = ApacheHttpClient.seatAvailability(content);
		String column = seatAvailability.getDataLists().getSeatList().get(0).getLocation().getColumn();
		SeatMapRowNbrType row = seatAvailability.getDataLists().getSeatList().get(0).getLocation().getRow().getNumber();
		System.out.println("----------> "+column+" "+row.getValue());
		
	}
	
}
