package teamparkinglot.parkinggo.bookmark.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookmarkStatusDto {
    private boolean bookmark;

    @Builder
    public BookmarkStatusDto(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
