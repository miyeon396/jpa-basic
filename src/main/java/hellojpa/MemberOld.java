package hellojpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //필수
public class MemberOld extends BaseEntity{

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    private LocalDate lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp; //메모리에서만 쓰겠음. 디비와 매핑하지 말 것.

}
