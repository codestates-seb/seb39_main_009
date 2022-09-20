package teamparkinglot.parkinggo.bookmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;
import teamparkinglot.parkinggo.bookmark.repository.BookmarkRepository;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking.service.ParkingService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberService memberService;
    private final ParkingService parkingService;


    @Transactional
    public void saveBookmark(String email, long parkingId) {

        checkBookmark(email, parkingId);

        Member member = memberService.findVerifiedMember(email);
        Parking parking = parkingService.findVerifiedParking(parkingId);
        Bookmark bookmark = new Bookmark(member, parking);

        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteBookmark(String email, long id) {

        Bookmark bookmark = bookmarkRepository.findByEmailAndParking(email, id).orElseThrow(
                () -> new BusinessException(ExceptionCode.BOOKMARK_NOT_EXISTS)
        );

        bookmarkRepository.delete(bookmark);
    }

    private void checkBookmark(String email, long parkingId) {
        Optional<Bookmark> find = bookmarkRepository.findByEmailAndParking(email, parkingId);

        if (find.isPresent()) {
            throw new BusinessException(ExceptionCode.ALREADY_EXISTS_BOOKMARK);
        }
    }

    public List<Bookmark> getBookmarkList(String email) {

        return bookmarkRepository.findByEmail(email);
    }
}
