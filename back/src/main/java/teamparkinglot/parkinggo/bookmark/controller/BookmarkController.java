package teamparkinglot.parkinggo.bookmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkIdDto;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkStatusDto;
import teamparkinglot.parkinggo.bookmark.mapper.BookmarkMapper;
import teamparkinglot.parkinggo.bookmark.service.BookmarkService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity postBookmark(@RequestBody BookmarkIdDto bookmarkIdDto,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {

        bookmarkService.saveBookmark(principalDetails.getUsername(), bookmarkIdDto.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/bookmark/{parkingId}")
    public ResponseEntity deleteBookmark(@PathVariable long parkingId,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

        bookmarkService.deleteBookmark(principalDetails.getUsername(), parkingId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bookmark")
    public ResponseEntity getBookmarkList(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getUsername();

        List<BookmarkResDto> bookmarkList = bookmarkService.getBookmarkList(email);

        return new ResponseEntity(bookmarkList, HttpStatus.OK);
    }

    @GetMapping("/bookmarkCheck/{parkingId}")
    public ResponseEntity getBookmarkStatus(@PathVariable long parkingId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (principalDetails != null) {
            BookmarkStatusDto bookmarkStatusDto = bookmarkService.checkBookmarkStatus(principalDetails.getUsername(), parkingId);
            return new ResponseEntity<>(bookmarkStatusDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(new BookmarkStatusDto(), HttpStatus.OK);
    }
}
