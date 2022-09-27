package teamparkinglot.parkinggo.parking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.history.History;
import teamparkinglot.parkinggo.history.HistoryRepository;
import teamparkinglot.parkinggo.history.HistoryRepositoryQueryDsl;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.mapper.ParkingMapper;
import teamparkinglot.parkinggo.parking.repository.ParkingQueryDsl;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.parking_place.ParkingPlaceRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;
import teamparkinglot.parkinggo.test.DbDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingQueryDsl parkingQueryDsl;
    private final ReservationService reservationService;
    private final MemberService memberService;
    private final HistoryRepositoryQueryDsl historyRepositoryQueryDsl;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;

    private final ParkingMapper parkingMapper;

    public List<Parking> findByCond(ParkingCondDto parkingCondDto) {

        List<Parking> parkings = parkingRepository.testMethod(parkingCondDto.getRegion(), parkingCondDto.getParkingStartTime(), parkingCondDto.getParkingEndTime());

        return parkings;
    }

    public List<ParkingRecentDto> findRecentSearches(String email) {

        if (email == null) {
            return new ArrayList<>();
        }

        return historyRepositoryQueryDsl.findRecentSearch(email);
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

        List<ValidNum> validNums = parkingPlaceRepository.findByParkingId(parking.getId()).stream()
                .map(e -> new ValidNum(e.getNumber()))
                .collect(Collectors.toList());

        return new ParkingMapDto(parking.getParkingMap(), validNums);
    }

    public CreateReservDto createReservation(Long id, ParkingDateTimeDto parkingDateTimeDto, String email) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );
        ParkingPlace parkingPlace = parkingPlaceRepository.findParkingPlace(id, parkingDateTimeDto.getNumber());

        long time = ChronoUnit.MINUTES.between(parkingDateTimeDto.getParkingStartDateTime(), parkingDateTimeDto.getParkingEndDateTime());

        long price = parking.getBasicCharge() + ((time - parking.getBasicTime()) / parking.getAddUnitTime()) * parking.getAddUnitCharge();

        if(price >= parking.getDayMaxPrice()) price = parking.getDayMaxPrice();

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDateTime.now())
                .parkingStartDateTime(parkingDateTimeDto.getParkingStartDateTime())
                .parkingEndDateTime(parkingDateTimeDto.getParkingEndDateTime())
                .member(member)
                .parkingPlace(parkingPlace)
                .payOrNot(false)
                .price(price)
                .refundAgmt(true)
                .build();

        reservationRepository.save(reservation);

        return parkingMapper.reservationToCreateReservDto(reservation, member);
    }

    public void saveAllItem(DbDto dbDto) {

        Member member = memberService.findVerifiedMember("kwj1830@naver.com");

        dbDto.getRecords().stream()
                .forEach(e -> {
                    Parking parking = Parking.builder()
                            .parkingManagementNumber(e.getPrkplceNo())
                            .parkingName(e.getPrkplceNm())
                            .type(e.getPrkplceSe())
                            .address(new Address("", e.getRdnmadr(), e.getLnmadr()))
                            .capacity(e.getPrkcmprt())
                            .weekdayOpen(e.getWeekdayOperOpenHhmm())
                            .weekdayClose(e.getWeekdayOperColseHhmm())
                            .satClose(e.getSatOperOperOpenHhmm())
                            .satOpen(e.getSatOperCloseHhmm())
                            .sunOpen(e.getHolidayOperOpenHhmm())
                            .sunClose(e.getHolidayCloseOpenHhmm())
                            .basicTime(e.getBasicTime())
                            .basicCharge(e.getBasicCharge())
                            .addUnitTime(e.getAddUnitTime())
                            .addUnitCharge(e.getAddUnitCharge())
                            .dayMaxPrice(e.getDayCmmtkt())
                            .latitude(e.getLatitude())
                            .longitude(e.getLongitude())
                            .member(member)
                            .phoneNumber(e.getPhoneNumber())
                            .partnership(false)
                            .build();

                    Optional<Parking> find = parkingRepository.findByParkingManagementNumber(parking.getParkingManagementNumber());

                    if (find.isEmpty()) {
                        parkingRepository.save(parking);
                    }
                });
    }
}