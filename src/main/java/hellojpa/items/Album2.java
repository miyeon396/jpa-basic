package hellojpa.items;

import hellojpa.Item2;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album2 extends Item2 {

    private String artist;
}
