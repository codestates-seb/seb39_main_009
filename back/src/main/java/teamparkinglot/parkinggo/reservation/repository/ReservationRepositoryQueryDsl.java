package teamparkinglot.parkinggo.reservation.repository;

import teamparkinglot.parkinggo.reservation.entity.Reservation;

public interface ReservationRepositoryQueryDsl {

    Reservation findReservation(Long id);
}
