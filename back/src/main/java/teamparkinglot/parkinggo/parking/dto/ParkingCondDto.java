package teamparkinglot.parkinggo.parking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ParkingCondDto {

    private String region;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime parkingStartTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime parkingEndTime;
    private String sort;
    private String crtLocation;

    public ParkingCondDto(String region, String parkingStartDateTime, String parkingEndDateTime, String sort, String crtLocation) {

        parkingStartDateTime += ":00";
        parkingEndDateTime += ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.region = region;
        this.parkingStartTime = LocalDateTime.parse(parkingStartDateTime, formatter);
        this.parkingEndTime = LocalDateTime.parse(parkingEndDateTime, formatter);
        this.sort = sort;
        this.crtLocation = crtLocation;
    }
}
