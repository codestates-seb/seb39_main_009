package teamparkinglot.parkinggo.bookmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;
import teamparkinglot.parkinggo.bookmark.service.BookmarkService;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity postBookmark(long id,
                                       Authentication authentication) {

        loginCheck(authentication);
        PrincipalDetails principalDetails = getPrincipalDetails(authentication);

        bookmarkService.saveBookmark(principalDetails.getUsername(), id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/bookmark")
    public ResponseEntity deleteBookmark(long id,
                                         Authentication authentication) {

        loginCheck(authentication);
        PrincipalDetails principalDetails = getPrincipalDetails(authentication);

        bookmarkService.deleteBookmark(principalDetails.getUsername(), id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sidebar/bookmark")
    public ResponseEntity getBookmarkList(Authentication authentication) {

        loginCheck(authentication);
        PrincipalDetails principalDetails = getPrincipalDetails(authentication);

        List<Bookmark> bookmarkList = bookmarkService.getBookmarkList(principalDetails.getUsername());

        return new ResponseEntity(bookmarkList, HttpStatus.OK);
    }

    private PrincipalDetails getPrincipalDetails(Authentication authentication) {
        return (PrincipalDetails) authentication.getPrincipal();
    }

    private static void loginCheck(Authentication authentication) {
        if (authentication == null) {
            throw new BusinessException(ExceptionCode.NEED_LOGIN);
        }
    }
}
