package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

//    @Column(name = "MEMBER_ID")
//    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID") //테이블 MEMBER : ORDER = 1 : N -> ORDER에 MemberID외래키 갖고있음 난(=Order) 주인이야 -> JoinCloumn
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Order를 생성해서 delivery에 넣을 때 자동으로 둘다 저장
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //order를 생헝할 때 orderItem도 생성 persist는 Order 한번만
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) { //양방향 연관관계가 잘 걸리게
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
