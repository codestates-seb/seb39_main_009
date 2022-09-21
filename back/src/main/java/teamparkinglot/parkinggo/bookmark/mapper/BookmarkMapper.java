package teamparkinglot.parkinggo.bookmark.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;

@Component
public class BookmarkMapper {

    public BookmarkResDto BookmarkToBookmarkResDto(Bookmark bookmark) {
        return BookmarkResDto.builder()
                .id(bookmark.getId())
                .name(bookmark.getParking().getName())
                .address(bookmark.getParking().getAddress().getParcel() + " " + bookmark.getParking().getAddress().getZipcode())
                .build();
    }

}
