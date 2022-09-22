package teamparkinglot.parkinggo.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.history.History;
import teamparkinglot.parkinggo.history.HistoryRepository;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.service.ParkingService;

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
            return;
        }

        History history = new History(member, parking);

        historyRepository.save(history);
    }

}
