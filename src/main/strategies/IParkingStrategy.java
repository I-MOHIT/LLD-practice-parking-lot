package main.strategies;

import main.models.ParkingSlot;
import main.models.Vehicle;

import java.util.Map;
import java.util.UUID;

public interface IParkingStrategy {
    public abstract ParkingSlot park(Map<UUID, ParkingSlot> parkingSlotMap, Vehicle vehicle);
}
