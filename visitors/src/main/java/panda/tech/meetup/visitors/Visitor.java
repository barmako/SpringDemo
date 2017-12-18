package panda.tech.meetup.visitors;

public class Visitor {

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
}
