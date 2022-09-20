package teamparkinglot.parkinggo.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.bookmark.service.BookmarkService;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

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


    private PrincipalDetails getPrincipalDetails(Authentication authentication) {
        return (PrincipalDetails) authentication.getPrincipal();
    }

    private static void loginCheck(Authentication authentication) {
        if (authentication == null) {
            throw new BusinessException(ExceptionCode.NEED_LOGIN);
        }
    }
}
