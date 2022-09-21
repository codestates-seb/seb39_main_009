package teamparkinglot.parkinggo.parking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.history.History;
import teamparkinglot.parkinggo.history.HistoryRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingQueryDsl;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingQueryDsl parkingQueryDsl;
    private final ReservationService reservationService;
    private final HistoryRepository historyRepository;
    private final MemberService memberService;
    private final ReviewRepository reviewRepository;

    public List<Parking> findByCond(ParkingCondDto parkingCondDto) {

        LocalDateTime dtoStartTime = parkingCondDto.getParkingStartTime();
        LocalDateTime dtoEndTime = parkingCondDto.getParkingEndTime();

        List<Parking> byRegion = parkingQueryDsl.findByRegion(parkingCondDto.getRegion());

        List<ParkingReserv> reservationList = byRegion.stream()
                .map(e -> new ParkingReserv(e.getId(), reservationService.findByParkingId(e.getId())))
                .collect(Collectors.toList());

        List<Long> realParking = new ArrayList();

        for (ParkingReserv parkingReserv : reservationList) {
            boolean flag = true;

            List<Reservation> reservations = parkingReserv.getReservations();

            for (Reservation reservation : reservations) {
                LocalDateTime reservStart = reservation.getParkingStartTime();
                LocalDateTime reservEndTime = reservation.getParkingEndTime();

                if (!(dtoStartTime.isBefore(reservStart) || dtoStartTime.isAfter(reservEndTime) || dtoStartTime.isEqual(reservEndTime))
                && !(dtoEndTime.isBefore(reservStart) || dtoEndTime.isAfter(reservEndTime) || dtoEndTime.isEqual(reservStart))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                realParking.add(parkingReserv.getParkingId());
            }
        }

        parkingRepository.findAllByid(realParking);

        return realParking.stream()
                .map(e -> parkingRepository.findById(e).orElseThrow(
                        () -> new BusinessException(ExceptionCode.SEARCH_ERROR)
                ))
                .collect(Collectors.toList());
    }

    public List<ParkingRecentDto> findRecentSearches(String email) {

        if (email == null) {
            return new ArrayList<>();
        }

        List<History> histories = historyRepository.findByMemberEmail(email);

        return histories.stream()
                .map(e -> new ParkingRecentDto(e.getParking().getName(), e.getParking().getAddress().getParcel()))
                .collect(Collectors.toList());
    }

    /**
     * 주차장 기본 검색
     * @param id
     */
    public Parking findById(Long id) {
        return parkingRepository.findById(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );
    }

    public Parking findVerifiedParking(long id) {
        return parkingRepository.findById(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );
    }

    public ParkingMapDto findMap(long id) {

        Parking parking = findVerifiedParking(id);
        List<ValidNum> validNums = reservationService.findByParkingId(id).stream()
                .map(e -> new ValidNum(e.getParkingPlace().getNumber()))
                .collect(Collectors.toList());

        return new ParkingMapDto(parking.getParkingMap(), validNums);
    }
}
