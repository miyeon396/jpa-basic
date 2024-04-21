package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class Address2 {

    private String city;
    private String street;
    private String zipcode;
}
