package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Child2 {

    @Id
    @GeneratedValue
    @Column(name="child_id")
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private Parent2 parent;
}
