package teamparkinglot.parkinggo.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Items {

    String prkplceNo;
    String prkplceNm;
    String prkplceSe;
    String prkplceType;
    String rdnmadr;
    String lnmadr;
    int prkcmprt;
    String weekdayOperOpenHhmm;
    String weekdayOperColseHhmm;
    String satOperOperOpenHhmm;
    String satOperCloseHhmm;
    String holidayOperOpenHhmm;
    String holidayCloseOpenHhmm;
    int basicTime;
    int basicCharge;
    int addUnitTime;
    int addUnitCharge;
    int dayCmmtkt;
    String phoneNumber;
    Double latitude;
    Double longitude;

}
