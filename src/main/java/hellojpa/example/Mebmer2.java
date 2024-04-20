package hellojpa.example;

import jakarta.persistence.*;

@Entity
public class Mebmer2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team2 team;

    //객체를 테이블에 맞추어 모델링 - 외래키 식별자를 직접 다룸 setTeam이라고 해야할거 같은데 setTeamId를 하게 됨

}
