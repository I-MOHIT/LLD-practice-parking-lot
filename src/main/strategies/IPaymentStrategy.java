package main.strategies;

import main.models.ParkingSlot;

public interface IPaymentStrategy {
    public abstract double payment(ParkingSlot parkingSlot);
}
