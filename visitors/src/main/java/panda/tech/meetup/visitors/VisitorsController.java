package panda.tech.meetup.visitors;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorsController {

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
    public void post(@RequestBody Visitor visitor) {
        visitors.add(visitor);
    }
}
