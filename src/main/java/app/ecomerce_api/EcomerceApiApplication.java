package app.ecomerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcomerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApiApplication.class, args);
	}

}
