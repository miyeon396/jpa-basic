package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Period2 {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
