package model;

import java.util.Objects;

public class User implements Identifyable{

    private final int id;
    private final String name;
    private int points;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.points = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
