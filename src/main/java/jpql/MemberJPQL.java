package jpql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(exclude = "team")
public class MemberJPQL {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private TeamJPQL team;

    public void changeTeam(TeamJPQL team) { // 연관관계 편의 메서드
        this.team = team;
        team.getMembers().add(this);
    }


}
