package finalmission.reservation.domain;


import finalmission.member.domain.Member;
import finalmission.room.domain.Room;
import finalmission.time.domain.Time;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_id")
    private Time time;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;


    Reservation(final Room room, final LocalDate date, final Time time, final Member member) {
        this.room = room;
        this.date = date;
        this.time = time;
        this.member = member;
    }

    public static Reservation makeReservation(
            final Room room,
            final LocalDate date,
            final Time time,
            final Member member
    ) {
        return new Reservation(room, date, time, member);
    }

    public boolean isReservedBy(final Member member) {
        return this.member.equals(member);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", date=" + date +
                ", time=" + time +
                ", member=" + member +
                '}';
    }
}
