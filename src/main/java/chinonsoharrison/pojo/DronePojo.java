package chinonsoharrison.pojo;

import chinonsoharrison.enums.Model;
import chinonsoharrison.enums.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DronePojo {
    private String serialNumber;

    private Model model;

    private float weightLimit;

    private int batteryCapacity;

    private State state;
}
