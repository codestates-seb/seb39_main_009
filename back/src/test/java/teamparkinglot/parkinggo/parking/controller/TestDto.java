package teamparkinglot.parkinggo.parking.controller;


public class TestDto {
    private String parkingStartDateTime;
    private String parkingEndDateTime;
    private Integer number;

    public TestDto(String parkingStartDateTime, String parkingEndDateTime, Integer number) {
        this.parkingStartDateTime = parkingStartDateTime;
        this.parkingEndDateTime = parkingEndDateTime;
        this.number = number;
    }
}
