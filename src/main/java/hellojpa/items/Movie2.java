package hellojpa.items;

import hellojpa.Item2;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie2 extends Item2 {
    private String director;
    private String actor;
}
