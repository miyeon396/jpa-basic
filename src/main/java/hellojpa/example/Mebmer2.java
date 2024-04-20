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
    private Team2 team; //진짜 매핑 //(테이블에서) 외래키가 있는 쪽이 주인 -> 일케해야 안헷갈림

    public void changeTeam(Team2 team) {
        this.team = team;
        team.getMembers().add(this); //양방향 연관관계시 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자. 그리고 그걸 각각 하기보다 연관관계 편의 메소드로 제공하라
    }

    //객체를 테이블에 맞추어 모델링 - 외래키 식별자를 직접 다룸 setTeam이라고 해야할거 같은데 setTeamId를 하게 됨

}
