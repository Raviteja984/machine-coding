package model;

import enums.Direction;
import enums.ElevatorStatus;

public class Elevator implements Identifyable{


    private final int id;
    private Floor currentFloor;
    private ElevatorStatus status;
    private Direction direction;

    public Elevator(int id, Floor currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.status = ElevatorStatus.IDLE;
        this.direction = Direction.NONE;
    }

    @Override
    public int getId() {
        return id;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    

}
