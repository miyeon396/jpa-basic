package hellojpa;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //default SINGLE_TABLE
public class Item2 {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
