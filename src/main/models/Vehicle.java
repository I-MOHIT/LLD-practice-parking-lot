package main.models;

import java.util.UUID;

public class Vehicle {
    private UUID vehicleId;
    private String colour;
    private SizeType sizeType;

    public Vehicle(String colour, SizeType sizeType) {
        this.vehicleId = UUID.randomUUID();
        this.colour = colour;
        this.sizeType = sizeType;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }
}
