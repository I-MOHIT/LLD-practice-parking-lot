package main.models;

import java.util.Date;
import java.util.UUID;

public class ParkingSlot {
    private UUID parkingSlotId;
    private boolean isAvailable;
    private Vehicle vehicle;
    private Date startTime;
    private SizeType sizeType;

    public ParkingSlot() {
        this.parkingSlotId = UUID.randomUUID();
        this.isAvailable = true;
        this.vehicle = null;
        this.startTime = null;
        this.sizeType = null;
    }

    public UUID getParkingSlotId() {
        return parkingSlotId;
    }

    public void setParkingSlotId(UUID parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }
}
