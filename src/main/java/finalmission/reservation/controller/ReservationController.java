package finalmission.reservation.controller;

import finalmission.auth.infrastructure.methodargument.AuthorizedMember;
import finalmission.auth.infrastructure.methodargument.MemberPrincipal;
import finalmission.reservation.dto.request.ReservationCreationRequest;
import finalmission.reservation.dto.response.ReservationCreationResponse;
import finalmission.reservation.service.facade.ReservationServiceFacade;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationController {

    private final ReservationServiceFacade reservationServiceFacade;

    @PostMapping
    public ResponseEntity<ReservationCreationResponse> create(
            @RequestBody @Valid ReservationCreationRequest request,
            @AuthorizedMember MemberPrincipal memberPrincipal
    ) {
        ReservationCreationResponse response = reservationServiceFacade.create(request, memberPrincipal);
        return ResponseEntity.created(URI.create("/reservations/" + response.id())).body(response);
    }
}
