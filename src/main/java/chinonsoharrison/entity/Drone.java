package chinonsoharrison.entity;


import chinonsoharrison.enums.Model;
import chinonsoharrison.enums.State;
import chinonsoharrison.pojo.Medication;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@Entity
@Table(name="drone_tb")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "serial_number" , columnDefinition = "VARCHAR(100)")
    @Max(100)
    private String serialNumber;

    @Column(name = "model" , columnDefinition = "VARCHAR(20)")
    private Model model;

    @Column(name = "weight_limit")
    @Max(value=500, message="Maximum weight allowed is 500gr")
    private float weightLimit;

    @Column(name = "battery_capacity" , columnDefinition = "VARCHAR(10)")
    @Max(100)
    @Min(0)
    private long batteryCapacity;

    @Column(name = "state" , columnDefinition = "VARCHAR(20)")
    private State state;

    @Type( type = "json" )
    @Column( name = "medications",columnDefinition = "json")
    private Medication medications;
}
