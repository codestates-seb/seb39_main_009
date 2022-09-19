package teamparkinglot.parkinggo.parking_place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParkingPlace is a Querydsl query type for ParkingPlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParkingPlace extends EntityPathBase<ParkingPlace> {

    private static final long serialVersionUID = -550847997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParkingPlace parkingPlace = new QParkingPlace("parkingPlace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final teamparkinglot.parkinggo.parking.entity.QParking parking;

    public final StringPath sector = createString("sector");

    public QParkingPlace(String variable) {
        this(ParkingPlace.class, forVariable(variable), INITS);
    }

    public QParkingPlace(Path<? extends ParkingPlace> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParkingPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParkingPlace(PathMetadata metadata, PathInits inits) {
        this(ParkingPlace.class, metadata, inits);
    }

    public QParkingPlace(Class<? extends ParkingPlace> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parking = inits.isInitialized("parking") ? new teamparkinglot.parkinggo.parking.entity.QParking(forProperty("parking"), inits.get("parking")) : null;
    }

}

