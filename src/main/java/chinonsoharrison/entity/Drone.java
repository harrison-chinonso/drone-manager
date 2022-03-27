package chinonsoharrison.entity;


import chinonsoharrison.enums.Model;
import chinonsoharrison.enums.State;
import chinonsoharrison.pojo.Medication;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(columnDefinition = "VARCHAR(100)")
    @Max(100)
    private String serialNumber;

    @Column(columnDefinition = "VARCHAR(20)")
    private Model model;

    @Max(value=500, message="Maximum weight allowed is 500gr")
    private float weightLimit;

    @Column(columnDefinition = "VARCHAR(10)")
    @Max(100)
    @Min(0)
    private int batteryCapacity;

    @Column(columnDefinition = "VARCHAR(20)")
    private State state;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private Medication medications;
}
