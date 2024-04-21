package jpql;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TeamJPQL {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberJPQL> members = new ArrayList<>();
}
