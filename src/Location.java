

public class Location {
    private int id;
    private String name;
    private boolean isBusStop;

    public Location(int id, String name, boolean isBusStop) {
        this.id = id;
        this.name = name;
        this.isBusStop = isBusStop;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isBusStop() { return isBusStop; }
}
