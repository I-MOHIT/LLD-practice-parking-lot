package main;

import main.models.ParkingSlot;
import main.models.Vehicle;

import java.util.List;
import java.util.UUID;

public class OutputPrinter {
    public void hello() {
        System.out.println("Hello!");
    }

    public void exit() {
        System.out.println("Bye!");
    }

    public void createParkingLot(int slots) {
        System.out.println("Created a parking lot having " + slots + " slot capacity");
    }

    public void park(Vehicle vehicle, ParkingSlot parkingSlot) {
        System.out.println("Vehicle having colour " + vehicle.getColour() + " and id " + vehicle.getVehicleId() + " has been parked at the parking slot " + parkingSlot.getParkingSlotId());
    }

    public void unpark(UUID parkingSlotId) {
        System.out.println("Parking Slot having id " + parkingSlotId.toString() + " is available now");
    }

    public void slotStatus(UUID parkingSlotId, boolean slotStatus) {
        System.out.println("Parking Slot having id " + parkingSlotId.toString() + " has the availability status - " + slotStatus);
    }

    public void parkingLotStatus(List<ParkingSlot> parkingSlotList) {
        System.out.println("The status of the parking slots availability are -");
        for (ParkingSlot parkingSlot : parkingSlotList) {
            System.out.println(parkingSlot.getParkingSlotId().toString() + " - " + parkingSlot.isAvailable() + " - " + parkingSlot.getSizeType().toString());
        }
    }
}
