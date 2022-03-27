package chinonsoharrison.pojo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.Pattern;


@Data
@Builder
public class Medication {
    @Pattern(message="allowed only letters, numbers, ‘-‘, ‘_’", regexp ="[a-zA-Z0-9-_]{1,100}")
    private String name;

    private float weight;

    @Pattern(message="allowed only upper case letters, underscore and numbers", regexp ="[A-Z0-9_]{1,100}")
    private String code;

    private Byte[] image; //picture of the medication case
}
