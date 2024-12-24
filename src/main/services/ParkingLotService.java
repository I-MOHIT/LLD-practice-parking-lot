package main.services;

import main.exceptions.InvalidSlotException;
import main.exceptions.SlotAlreadyAvailableException;
import main.models.ParkingLot;
import main.models.ParkingSlot;
import main.models.Vehicle;
import main.strategies.IParkingStrategy;
import main.strategies.IPaymentStrategy;

import java.util.Map;
import java.util.UUID;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private IParkingStrategy parkingStrategy;
    private IPaymentStrategy paymentStrategy;

    public void createParkingLot(ParkingLot parkingLot, IParkingStrategy parkingStrategy, IPaymentStrategy paymentStrategy) {
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        this.paymentStrategy = paymentStrategy;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public IParkingStrategy getParkingStrategy() {
        return parkingStrategy;
    }

    public void setParkingStrategy(IParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public IPaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    synchronized public ParkingSlot park(Vehicle vehicle) {
        Map<UUID, ParkingSlot> parkingSlotMap = this.parkingLot.getParkingSlot();
        return this.parkingStrategy.park(parkingSlotMap, vehicle);
    }

    synchronized public void freeSlot(UUID parkingSlotId) {
        if (!this.parkingLot.getParkingSlot().containsKey(parkingSlotId)) {
            throw new InvalidSlotException();
        }
        if (this.parkingLot.getParkingSlot().get(parkingSlotId).isAvailable()) {
            throw new SlotAlreadyAvailableException();
        }
        ParkingSlot parkingSlot = this.parkingLot.getParkingSlot().get(parkingSlotId);
        System.out.println("Your payment is " + this.paymentStrategy.payment(parkingSlot));
        parkingSlot.setVehicle(null);
        parkingSlot.setAvailable(true);
        parkingSlot.setStartTime(null);
        this.parkingLot.getParkingSlot().put(parkingSlotId, parkingSlot);
    }
}
