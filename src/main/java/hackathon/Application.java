package hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	SeatService seatService() {
		return new SeatService();
	}
}
