package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded //생략가능하지만 명확하게 값 타입이라
    private Address address;

    @OneToMany(mappedBy = "member") //Table Member : Order = 1 : N -> Member(1) 나는 거울야 나는 외래키 가진게 엄써 -> mappedBy 저쪽(Order)에 있는 member에 의해 매핑되어써
    private List<Order> orders = new ArrayList<>(); // 비지니스 적으로 필요 없는거를 갖고있는게 좋지는 않음. 그러나 예시로 걍 한번 적어봄
}
