package finalmission.reservation.service;

import finalmission.member.domain.Member;
import finalmission.reservation.domain.Reservation;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.room.domain.Room;
import finalmission.time.domain.Time;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation create(
            final Room room,
            final LocalDate date,
            final Time time,
            final Member member
    ) {
        final Reservation reservation = Reservation.makeReservation(
                room,
                date,
                time,
                member
        );

        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
