package main.strategies;

import main.exceptions.NoSlotAvailableException;
import main.models.ParkingSlot;
import main.models.Vehicle;

import java.util.*;

public class AlternativeParkingStrategy implements IParkingStrategy{
    @Override
    synchronized public ParkingSlot park(Map<UUID, ParkingSlot> parkingSlotMap, Vehicle vehicle) {
        List<ParkingSlot> parkingSlotList = parkingSlotMap.values().stream().toList();
        List<ParkingSlot> availableParkingSlots = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            if (parkingSlot.isAvailable() && parkingSlot.getSizeType().ordinal() >= vehicle.getSizeType().ordinal()) {
                availableParkingSlots.add(parkingSlot);
            }
        }
        if (availableParkingSlots.isEmpty()) {
            throw new NoSlotAvailableException();
        }
        Random random  = new Random();
        int chosenParkingSlotIndex = random.nextInt(0, availableParkingSlots.size());
        ParkingSlot parkingSlotSelected = availableParkingSlots.get(chosenParkingSlotIndex);
        parkingSlotSelected.setVehicle(vehicle);
        parkingSlotSelected.setAvailable(false);
        parkingSlotSelected.setStartTime(new Date());
        parkingSlotMap.put(parkingSlotSelected.getParkingSlotId(), parkingSlotSelected);
        return parkingSlotSelected;
    }
}
