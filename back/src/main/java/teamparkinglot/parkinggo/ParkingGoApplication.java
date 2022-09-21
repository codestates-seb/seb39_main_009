package teamparkinglot.parkinggo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ParkingGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingGoApplication.class, args);
	}

}
