package panda.tech.meetup.intruderclassifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IntruderClassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntruderClassifierApplication.class, args);
    }
}
