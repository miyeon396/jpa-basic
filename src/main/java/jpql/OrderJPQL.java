package jpql;

import jakarta.persistence.*;

@Entity
public class OrderJPQL {

    @Id
    @GeneratedValue
    private Long id;

    private int orderAmount;

    @Embedded
    private AddressJPQL address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductJPQL product;
}
