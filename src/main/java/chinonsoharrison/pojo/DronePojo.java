package chinonsoharrison.pojo;

import lombok.Data;

@Data
public class DronePojo {

    private String serialNumber;

    private String model; //(Lightweight, Middleweight, Cruiserweight, Heavyweight);

    private String weightLimit;

    private long batteryCapacity;

    private String state;  //(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
}
