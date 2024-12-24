package main.strategies;

import main.models.ParkingSlot;

import java.util.Date;

public class AlternativePaymentStrategy implements IPaymentStrategy{
    @Override
    public double payment(ParkingSlot parkingSlot) {
        double timeParkedInSeconds = (double) (new Date().getTime() - parkingSlot.getStartTime().getTime()) / 1000.0;
        return switch (parkingSlot.getSizeType()) {
            case LARGE -> 100 + timeParkedInSeconds * 0.03;
            case MEDIUM -> 75 + timeParkedInSeconds * 0.02;
            case SMALL -> 50 + timeParkedInSeconds * 0.01;
            default -> 0;
        };
    }
}
