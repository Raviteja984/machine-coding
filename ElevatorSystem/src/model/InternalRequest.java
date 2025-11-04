package model;

public class InternalRequest extends Request{
    
    private final Elevator elevator; // requested inside which elevator
    private Floor destinationFloor;

    public InternalRequest(int id, Floor sourceFloor, Floor destinationFloor, Elevator elevator) {
        super(id, sourceFloor);
        this.elevator = elevator;
        this.destinationFloor = destinationFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
