package hellojpa.items;

import hellojpa.Item2;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book2 extends Item2 {

    private String author;
    private String isbn;
}
