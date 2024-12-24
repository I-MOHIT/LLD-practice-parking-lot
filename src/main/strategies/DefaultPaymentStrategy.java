package main.strategies;

import main.models.ParkingSlot;

public class DefaultPaymentStrategy implements IPaymentStrategy{
    @Override
    public double payment(ParkingSlot parkingSlot) {
        return 100.0;
    }
}
