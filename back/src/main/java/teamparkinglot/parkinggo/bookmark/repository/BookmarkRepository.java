package teamparkinglot.parkinggo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryQueryDsl {


    @Query("select b from Bookmark b where b.member.email = :email and b.parking.id = :parkingId")
    Optional<Bookmark> findByEmailAndParking(@Param("email") String email, @Param("parkingId") long parkingId);

    @Query("select b from Bookmark b where b.member.email = :email")
    List<Bookmark> findByEmail(@Param("email") String email);

}
