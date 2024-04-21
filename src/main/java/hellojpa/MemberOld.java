package hellojpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //필수
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
    private Address2 address;




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
