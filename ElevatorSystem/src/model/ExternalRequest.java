package model;

import enums.Direction;

public class ExternalRequest extends Request{
    
    private final Direction direction;

    public ExternalRequest(int id, Floor sourceFloor, Direction direction) {
        super(id, sourceFloor);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
