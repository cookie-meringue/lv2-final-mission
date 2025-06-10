package finalmission.reservation.service.facade;

import finalmission.auth.infrastructure.methodargument.MemberPrincipal;
import finalmission.exception.domain.BadRequestException;
import finalmission.exception.domain.ForbiddenException;
import finalmission.member.domain.Member;
import finalmission.member.service.MemberService;
import finalmission.reservation.domain.Reservation;
import finalmission.reservation.dto.request.ReservationCreationRequest;
import finalmission.reservation.dto.response.ReservationCreationResponse;
import finalmission.reservation.dto.response.ReservationResponse;
import finalmission.reservation.service.ReservationService;
import finalmission.room.domain.Room;
import finalmission.room.service.RoomService;
import finalmission.time.domain.Time;
import finalmission.time.service.TimeService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationServiceFacade {


    private final TimeService timeService;
    private final RoomService roomService;
    private final MemberService memberService;
    private final ReservationService reservationService;

    public ReservationCreationResponse create(
            final ReservationCreationRequest request,
            final MemberPrincipal memberPrincipal
    ) {

        final Room room = roomService.findById(request.roomId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 회의실입니다."));
        final Time time = timeService.findById(request.timeId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 시간입니다."));
        final Member member = memberService.findByPrincipal(memberPrincipal)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 사용자입니다."));
        final Reservation reservation = reservationService.create(
                room,
                request.date(),
                time,
                member
        );

        return ReservationCreationResponse.fromReservation(reservation);
    }

    public List<ReservationResponse> findAll() {
        final List<Reservation> reservations = reservationService.findAll();

        return reservations.stream()
                .map(ReservationResponse::fromReservation)
                .toList();
    }

    public List<ReservationResponse> findByMemberPrincipal(final MemberPrincipal memberPrincipal) {
        final Member member = memberService.findByPrincipal(memberPrincipal)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 사용자입니다."));
        final List<Reservation> reservations = reservationService.findAllByMember(member);

        return reservations.stream()
                .map(ReservationResponse::fromReservation)
                .toList();
    }

    public void delete(final Long id, final MemberPrincipal memberPrincipal) {
        final Member member = memberService.findByPrincipal(memberPrincipal)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 사용자입니다."));
        final Reservation reservation = reservationService.findById(id)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 예약입니다."));

        if (!reservation.isReservedBy(member)) {
            throw new ForbiddenException("권한이 없습니다.");
        }

        reservationService.delete(reservation);
    }
}
