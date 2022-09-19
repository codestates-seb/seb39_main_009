package teamparkinglot.parkinggo.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 1498011849L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final teamparkinglot.parkinggo.member.entity.QMember member;

    public final DateTimePath<java.time.LocalDateTime> parkingEndTime = createDateTime("parkingEndTime", java.time.LocalDateTime.class);

    public final teamparkinglot.parkinggo.parking_place.QParkingPlace parkingPlace;

    public final DateTimePath<java.time.LocalDateTime> parkingStartTime = createDateTime("parkingStartTime", java.time.LocalDateTime.class);

    public final BooleanPath payOrNot = createBoolean("payOrNot");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final BooleanPath refundAgmt = createBoolean("refundAgmt");

    public final DateTimePath<java.time.LocalDateTime> reservationDate = createDateTime("reservationDate", java.time.LocalDateTime.class);

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new teamparkinglot.parkinggo.member.entity.QMember(forProperty("member")) : null;
        this.parkingPlace = inits.isInitialized("parkingPlace") ? new teamparkinglot.parkinggo.parking_place.QParkingPlace(forProperty("parkingPlace"), inits.get("parkingPlace")) : null;
    }

}

