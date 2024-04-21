package hellojpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor //(access = AccessLevel.PROTECTED) //필수
public class MemberOld {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    //Period
    @Embedded
    private Period2 workPeriod;

    //주소
    @Embedded
    private Address2 homeAddress;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection //값 타입
//    @CollectionTable(name="ADDRESS2", joinColumns =
//        @JoinColumn(name = "MEMBER_ID"), foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private List<Address2> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<Address2Entity> addressHistory = new ArrayList<>();



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                column = @Column(name="WORK_CITY")),
            @AttributeOverride(name="street",
                    column = @Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column = @Column(name="WORK_ZIPCODE")),

    })
    private Address2 workAddress;




//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date createdDate;
////
////    private LocalDate lastModifiedDate;
//
//    @Lob
//    private String description;
//
//    @Transient
//    private int temp; //메모리에서만 쓰겠음. 디비와 매핑하지 말 것.

}
