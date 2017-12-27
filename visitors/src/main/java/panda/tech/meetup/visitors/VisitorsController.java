package panda.tech.meetup.visitors;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorsController {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    private static List<Visitor> visitors;

    static {
        visitors = new ArrayList<>();
        visitors.add(new Visitor("Bar", "Captain"));
        visitors.add(new Visitor("Weiss", "Captain"));
        visitors.add(new Visitor("Bukchin", "Kama"));
        visitors.add(new Visitor("Rokah", "Rabat"));
    }


    @RequestMapping("/")
    public List<Visitor> get(@RequestParam(value = "name", defaultValue = "World") String name) {
        return visitors;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public Boolean post(@RequestBody Visitor visitor) {
        visitors.add(visitor);
        return isIntruder(visitor);
    }

    private boolean isIntruder(Visitor visitor) {
        String homePageUrl = getClassifierUrl();

        return restTemplate.postForObject(homePageUrl, visitor, Boolean.class);
    }

    private String getClassifierUrl() {
        InstanceInfo classifier = eurekaClient.getNextServerFromEureka("classifier", false);
        String url = classifier.getHomePageUrl();
        System.out.println("Url by Eureka " + url);
        return url;
    }
}
