package panda.tech.meetup.intruderclassifier;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

    @RequestMapping(method = RequestMethod.POST)
    public Boolean isIntruder(@RequestBody Visitor visitor) {
        System.out.println("Is intruder classification for " + visitor);
        if ("Samar".equalsIgnoreCase(visitor.rank) && "Jenny".equalsIgnoreCase(visitor.name))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }


    public static class Visitor {
        private String name;
        private String rank;

        public Visitor() {
        }

        public Visitor(String name, String rank) {

            this.name = name;
            this.rank = rank;
        }

        public String getName() {
            return name;
        }

        public String getRank() {
            return rank;
        }

        @Override
        public String toString() {
            return String.format("{Visitor: name: %s rank: %s}", name, rank);
        }
    }


}
