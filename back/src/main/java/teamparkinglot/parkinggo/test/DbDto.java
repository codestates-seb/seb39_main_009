package teamparkinglot.parkinggo.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DbDto {

    private List<Items> records;

}
