package panda.tech.meetup.visitors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VisitorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorsApplication.class, args);
	}
}
