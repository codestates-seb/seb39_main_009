package teamparkinglot.parkinggo.history.repository;

import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;

import java.util.List;

public interface HistoryRepositoryQueryDsl {

    List<ParkingRecentDto> findRecentSearch(String email);
}
