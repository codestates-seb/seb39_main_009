package teamparkinglot.parkinggo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


    Optional<Bookmark> findByEmailAndParking(String email, long parkingId);
}
