package teamparkinglot.parkinggo.bookmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;
import teamparkinglot.parkinggo.bookmark.mapper.BookmarkMapper;
import teamparkinglot.parkinggo.bookmark.service.BookmarkService;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper mapper;

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

    @GetMapping("/bookmark")
    public ResponseEntity getBookmarkList(Authentication authentication) {

        loginCheck(authentication);
        PrincipalDetails principalDetails = getPrincipalDetails(authentication);

        List<BookmarkResDto> bookmarkList = bookmarkService.getBookmarkList(principalDetails.getUsername()).stream()
                .map(e -> mapper.BookmarkToBookmarkResDto(e))
                .collect(Collectors.toList());


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
