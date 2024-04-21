package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class Period2 {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
