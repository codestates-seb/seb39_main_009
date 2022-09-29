package teamparkinglot.parkinggo.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BookmarkResDto {

    private long parkingId;
    private String name;
    private String address;

    @Builder
    public BookmarkResDto(long parkingId, String name, String address) {
        this.parkingId = parkingId;
        this.name = name;
        this.address = address;
    }
}
