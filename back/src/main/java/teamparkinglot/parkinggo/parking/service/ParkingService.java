package teamparkinglot.parkinggo.parking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.dto.ParkingCondDto;
import teamparkinglot.parkinggo.parking.dto.ParkingFindDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingQueryDsl;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingQueryDsl parkingQueryDsl;
    private final MemberService memberService;
    private final ReviewRepository reviewRepository;

    public List<Parking> findByCond(ParkingCondDto parkingCondDto, String email) {

        List<Parking> byRegion = parkingQueryDsl.findByRegion(parkingCondDto.getRegion());

        for (Parking parking : byRegion) {

        }



        return byRegion;
    }

    // 이용자 검색 내역 (이거 어캄 ㅋ)
    public List<Parking> findRecentSearches(String email) {

        if (email == null) {
            return parkingRepository.findTop10ByOrderById();
        }

        return null;
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
}
