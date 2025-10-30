package model;

import java.util.List;

import enums.Difficulty;

public class Problem implements Identifyable{

    private final int id;
    private String title;
    private String description;
    private final List<TestCase> testCases;
    private int points;
    private Difficulty difficulty;
    
    public Problem(int id, String title, String description, List<TestCase> testCases, Difficulty difficulty, int points) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.testCases = testCases;
        this.difficulty = difficulty;
        this.points = points;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Problem [title=" + title + ", description=" + description + ", points=" + points + ", difficulty="
                + difficulty + "]";
    }
    
}
