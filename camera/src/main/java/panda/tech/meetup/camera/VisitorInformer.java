package panda.tech.meetup.camera;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class VisitorInformer {

    private static Random random = new Random();

    private static String[] names = new String[]{"Jenny", "Karpf", "Hagbi", "Iftah", "Dennis"};
    private static String[] ranks = new String[]{"Rabat", "Samal", "Samar", "Rasal", "Kama"};


    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EurekaClient eurekaClient;

    @Scheduled(initialDelay = 3000, fixedRate = 3000)
    public void informVisitor() {
        String visitor = getVisitor();
        System.out.println("POSTING visitor " + visitor);
        postVisitor(visitor);
    }


    private void postVisitor(String visitor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(visitor, headers);
        try {
            String s = restTemplate.postForObject(getVisitorUrl(), entity, String.class);
            System.out.println("Result =" + s);
        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
    }

    private String getVisitorUrl() {
        InstanceInfo visitor = eurekaClient.getNextServerFromEureka("visitor", false);
        String homePageUrl = visitor.getHomePageUrl();
        System.out.println("Visitors url by eureka - " + homePageUrl);
        return homePageUrl + "/visitor/";
    }

    private String getVisitor() {
        return String.format("{\"name\": \"%s\", \"rank\":\"%s\"}", getRandom(names), getRandom(ranks));
    }

    private static String getRandom(String[] array) {
        return array[random.nextInt(array.length)];
    }
}
