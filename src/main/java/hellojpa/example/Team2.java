package hellojpa.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team2 {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") //나의 반대편 변수 team과 매핑되어있어
    private List<Mebmer2> members = new ArrayList<>(); //초기화 해놓기. //가짜 매핑 읽기만 하는 것

}
