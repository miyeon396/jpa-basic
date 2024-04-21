package jpql;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class AddressJPQL {

    private String city;
    private String street;
    private String zipcode;
}
