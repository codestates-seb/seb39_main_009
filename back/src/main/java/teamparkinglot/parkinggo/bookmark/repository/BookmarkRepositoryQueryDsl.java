package teamparkinglot.parkinggo.bookmark.repository;

import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;

import java.util.List;

public interface BookmarkRepositoryQueryDsl {

    List<BookmarkResDto> findMyBookmarkListByEmail(String email);
}
