package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
public class Address {

    @Column(length=10)
    private String city;
    @Column(length=20)
    private String street;
    @Column(length=5) //공통으로 관리하기 좋음
    private String zipcode;

    public String fullAddress() { //의미있는 비지니스
        return getCity() + " " + getStreet() + " " + getZipcode();
    }
}
