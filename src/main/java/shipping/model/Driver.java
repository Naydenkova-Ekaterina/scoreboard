package shipping.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Driver {

    private int id;

    private String name;

    private String surname;

    private String status;

    private String workedHours;

    private String order;

}
