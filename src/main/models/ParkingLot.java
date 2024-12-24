package main.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private int capacity;
    private Map<UUID, ParkingSlot> parkingSlot;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingSlot = new HashMap<>();
        for (int i = 0; i < capacity; i++) {
            ParkingSlot currParkingSlot = new ParkingSlot();
            this.parkingSlot.put(currParkingSlot.getParkingSlotId(), currParkingSlot);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<UUID, ParkingSlot> getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(Map<UUID, ParkingSlot> parkingSlot) {
        this.parkingSlot = parkingSlot;
    }
}
