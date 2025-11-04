package model;

import java.time.LocalDateTime;

import enums.RequestStatus;

public abstract class Request implements Identifyable{

    private final int id;
    private final Floor sourceFloor;
    private final LocalDateTime requestedAt;
    private RequestStatus status;

    public Request(int id, Floor sourceFloor) {
        this.id = id;
        this.sourceFloor = sourceFloor;
        this.requestedAt = LocalDateTime.now();
        this.status = RequestStatus.PENDING;
    }

    @Override
    public int getId() {
        return id;
    }

    public Floor getSourceFloor() {
        return sourceFloor;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }



    public RequestStatus getStatus() {
        return status;
    }



    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    
    
    
}
