package main.strategies;

import main.exceptions.NoSlotAvailableException;
import main.models.ParkingSlot;
import main.models.Vehicle;

import java.util.*;

public class DefaultParkingStrategy implements IParkingStrategy{
    @Override
    synchronized public ParkingSlot park(Map<UUID, ParkingSlot> parkingSlotMap, Vehicle vehicle) {
        List<ParkingSlot> parkingSlotList = new ArrayList<>(parkingSlotMap.values().stream().toList());
        parkingSlotList.sort(Comparator.comparingInt(parkingSlot -> parkingSlot.getSizeType().ordinal()));
        ParkingSlot biggerParkingSlot = null;
        for (ParkingSlot parkingSlot : parkingSlotList) {
            if (parkingSlot.isAvailable()) {
                if (parkingSlot.getSizeType().equals(vehicle.getSizeType())) {
                    parkingSlot.setVehicle(vehicle);
                    parkingSlot.setAvailable(false);
                    parkingSlot.setStartTime(new Date());
                    parkingSlotMap.put(parkingSlot.getParkingSlotId(), parkingSlot);
                    return parkingSlot;
                } else if (parkingSlot.getSizeType().ordinal() > vehicle.getSizeType().ordinal()) {
                    biggerParkingSlot = parkingSlot;
                    break;
                }
            }
        }
        if (biggerParkingSlot != null) {
            biggerParkingSlot.setVehicle(vehicle);
            biggerParkingSlot.setAvailable(false);
            biggerParkingSlot.setStartTime(new Date());
            parkingSlotMap.put(biggerParkingSlot.getParkingSlotId(), biggerParkingSlot);
            return biggerParkingSlot;
        }
        throw new NoSlotAvailableException();
    }
}
