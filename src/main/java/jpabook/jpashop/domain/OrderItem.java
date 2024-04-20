package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

//    @Column(name = "ORDER_ID")
//    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order; //Table OrderItem : Order = N : 1 -> OrderItem (N) -> 주인 -> @JoinCloumn

//    @Column(name = "ITEM_ID")
//    private Long itemId;// Table OrderItem : Item = N : 1 -> OrderItem(N) -> 외래키 갖고이씀(TEIM_ID) -> 주인 -> @JoinColumn

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;
}
