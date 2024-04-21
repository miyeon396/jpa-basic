package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address2 {

    private String city;
    private String street;
    private String zipcode;
}
