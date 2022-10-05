package teamparkinglot.parkinggo.history.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.history.entity.History;
import teamparkinglot.parkinggo.history.repository.HistoryRepository;
import teamparkinglot.parkinggo.history.repository.HistoryRepositoryQueryDsl;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HistoryServiceTest {

    @Autowired private HistoryRepository historyRepository;
    @Autowired private HistoryRepositoryQueryDsl historyRepositoryQueryDsl;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingRepository parkingRepository;

    @Test
    public void saveHistory() throws Exception {
        //given
        Member member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
        Parking parking = new Parking("1", "parkingName", new Address("1", "1", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parkingRepository.save(parking);
        //when
        History history = new History(member, parking);
        historyRepository.save(history);

        //then
        Optional<History> byMemberEmailAndParkingId = historyRepository.findByMemberEmailAndParkingId(member.getEmail(), parking.getId());
        assertEquals(byMemberEmailAndParkingId.get(), history);
    }

    @Test
    public void findRecentSearches() throws Exception {
        //given
        Member member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
        Parking parking = new Parking("1", "parkingName", new Address("1", "1", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parkingRepository.save(parking);
        History history = new History(member, parking);
        historyRepository.save(history);
        //when
        List<ParkingRecentDto> recentSearch = historyRepositoryQueryDsl.findRecentSearch(member.getEmail());
        //then
        assertEquals(recentSearch.get(0).getParkingId(), history.getParking().getId());
    }

}