package teamparkinglot.parkinggo.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.history.entity.History;
import teamparkinglot.parkinggo.history.repository.HistoryRepository;
import teamparkinglot.parkinggo.history.repository.HistoryRepositoryQueryDslImpl;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.service.ParkingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberService memberService;
    private final ParkingService parkingService;

    @Transactional
    public void saveHistory(long parkingId, String email) {

        Member member = memberService.findVerifiedMember(email);
        Parking parking = parkingService.findVerifiedParking(parkingId);

        Optional<History> historyIsThere = historyRepository.findByMemberEmailAndParkingId(email, parkingId);
        if (historyIsThere.isPresent()) {
            History history = historyIsThere.orElseThrow(() -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS));
            historyRepository.delete(history);
        }

        History history = new History(member, parking);

        historyRepository.save(history);
    }

    public List<ParkingRecentDto> findRecentSearches(String email) {

        if (email == null) {
            return new ArrayList<>();
        }

        return historyRepository.findRecentSearch(email);
    }

}
