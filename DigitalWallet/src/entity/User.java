package entity;

public class User implements Identifyable{

    private final int id;
    private final String name;

    public String getName() {
        return name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }
}
