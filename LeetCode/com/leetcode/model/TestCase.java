package model;

public class TestCase implements Identifyable{

    private final int id;
    private String input;
    private String expectedOutput;

    public TestCase(int id, String input, String expectedOutput) {
        this.id = id;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    @Override
    public String toString() {
        return "TestCase [id=" + id + ", input=" + input + ", expectedOutput=" + expectedOutput + "]";
    }
}
