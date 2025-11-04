package model;

public class Floor implements Identifyable{

    private final int id;
    private final int floorNumber;
    private boolean upButtonPressed;
    private boolean downButtonPressed;

    public Floor(int id, int floorNumber) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.upButtonPressed = false;
        this.downButtonPressed = false;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isUpButtonPressed() {
        return upButtonPressed;
    }

    public void setUpButtonPressed(boolean upButtonPressed) {
        this.upButtonPressed = upButtonPressed;
    }

    public boolean isDownButtonPressed() {
        return downButtonPressed;
    }

    public void setDownButtonPressed(boolean downButtonPressed) {
        this.downButtonPressed = downButtonPressed;
    }
}
