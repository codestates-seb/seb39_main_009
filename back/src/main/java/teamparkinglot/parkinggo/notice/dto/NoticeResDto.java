package teamparkinglot.parkinggo.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.notice.entity.NoticeType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResDto {
    private NoticeType type;
    private String title;
    private String body;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
