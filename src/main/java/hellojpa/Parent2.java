package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Parent2 {

    @Id
    @GeneratedValue
    @Column(name="parent_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child2> childList = new ArrayList<>();

    public void addChild(Child2 child) { //연관관계 메서드
        childList.add(child);
        child.setParent(this);
    }

}
