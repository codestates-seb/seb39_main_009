package teamparkinglot.parkinggo.bookmark.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.bookmark.controller.BookmarkController;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkStatusDto;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;
import teamparkinglot.parkinggo.bookmark.repository.BookmarkRepository;
import teamparkinglot.parkinggo.bookmark.repository.BookmarkRepositoryQueryDsl;
import teamparkinglot.parkinggo.member.controller.MemberController;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.mail.MailService;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.security.SecurityConfig;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookmarkServiceTest {

    @Autowired private BookmarkService bookmarkService;
    @Autowired private BookmarkRepository bookmarkRepository;
    @Autowired private BookmarkRepositoryQueryDsl bookmarkRepositoryQueryDsl;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingRepository parkingRepository;
    Member member;
    Parking parking;


    @BeforeEach
    void set() {
        member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
        parking = new Parking("1", "parkingName", new Address("1", "1", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parkingRepository.save(parking);
    }
    @Test
    public void saveBookmark() throws Exception {
        //given
        Bookmark bookmark = new Bookmark(member, parking);
        //when
        bookmarkRepository.save(bookmark);
        //then
        Optional<Bookmark> byEmailAndParkingTest = bookmarkRepository.findByEmailAndParking(member.getEmail(), parking.getId());

        assertEquals(bookmark.getMember().getEmail(), byEmailAndParkingTest.get().getMember().getEmail());
    }

    @Test
    public void deleteBookmark() throws Exception {
        //given
        Bookmark bookmark = new Bookmark(member, parking);
        bookmarkRepository.save(bookmark);
        Optional<Bookmark> byEmailAndParking = bookmarkRepository.findByEmailAndParking(member.getEmail(), parking.getId());
        //when
        bookmarkRepository.delete(byEmailAndParking.get());
        //then
        Optional<Bookmark> bookmarkOptional = bookmarkRepository.findByEmailAndParking(member.getEmail(), parking.getId());
        assertEquals(bookmarkOptional, Optional.empty());
    }

    @Test
    public void getBookmarkList() throws Exception {
        //given
        Bookmark bookmark = new Bookmark(member, parking);
        bookmarkRepository.save(bookmark);
        //when
        List<BookmarkResDto> myBookmarkListByEmail = bookmarkRepositoryQueryDsl.findMyBookmarkListByEmail(member.getEmail());
        //then
        assertEquals(myBookmarkListByEmail.get(0).getName(), bookmark.getParking().getParkingName());
    }

    @Test
    public void checkBookmarkStatus() throws Exception {
        //given
        Bookmark bookmark = new Bookmark(member, parking);
        bookmarkRepository.save(bookmark);
        BookmarkStatusDto bookmarkStatusDto = new BookmarkStatusDto();
        //when
        Optional<Bookmark> byEmailAndParking = bookmarkRepository.findByEmailAndParking(member.getEmail(), parking.getId());
        if (byEmailAndParking.isPresent()) {
            bookmarkStatusDto.setBookmark(true);
        }
        //then
        assertTrue(bookmarkStatusDto.isBookmark());
    }

}