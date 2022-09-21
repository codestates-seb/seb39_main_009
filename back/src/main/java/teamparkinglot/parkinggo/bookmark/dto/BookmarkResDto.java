package teamparkinglot.parkinggo.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookmarkResDto {

    private long id;
    private String name;
    private String address;

    @Builder
    public BookmarkResDto(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
