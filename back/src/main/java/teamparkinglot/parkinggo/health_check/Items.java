package teamparkinglot.parkinggo.health_check;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Items {

    private String prkplceNo;
    private String prkplceNm;
    private String prkplceSe;
    private String prkplceType;
    private String parkingChargeInfo;
    private String spacialManagement;
    private String metpay;
    private String rdnmadr;
    private String lnmadr;
    private int prkcmprt;
    private String weekdayOperOpenHhmm;
    private String weekdayOperColseHhmm;
    private String satOperOperOpenHhmm;
    private String satOperCloseHhmm;
    private String holidayOperOpenHhmm;
    private String holidayCloseOpenHhmm;
    private int basicTime;
    private int basicCharge;
    private int addUnitTime;
    private int addUnitCharge;
    private int dayCmmtkt;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;

}
