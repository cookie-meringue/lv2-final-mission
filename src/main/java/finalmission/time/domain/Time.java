package finalmission.time.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "time")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_at")
    private LocalTime startAt;

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", startAt=" + startAt +
                '}';
    }
}
