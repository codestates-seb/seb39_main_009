package teamparkinglot.parkinggo.parking.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParking is a Querydsl query type for Parking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParking extends EntityPathBase<Parking> {

    private static final long serialVersionUID = -175006519L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParking parking = new QParking("parking");

    public final QAddress address;

    public final NumberPath<Integer> addUnitCharge = createNumber("addUnitCharge", Integer.class);

    public final NumberPath<Integer> addUnitTime = createNumber("addUnitTime", Integer.class);

    public final NumberPath<Integer> basicCharge = createNumber("basicCharge", Integer.class);

    public final NumberPath<Integer> basicTime = createNumber("basicTime", Integer.class);

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final NumberPath<Integer> dayMaxPrice = createNumber("dayMaxPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final teamparkinglot.parkinggo.member.entity.QMember member;

    public final StringPath parkingManagementNumber = createString("parkingManagementNumber");

    public final StringPath parkingMap = createString("parkingMap");

    public final StringPath parkingName = createString("parkingName");

    public final BooleanPath partnership = createBoolean("partnership");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath satClose = createString("satClose");

    public final StringPath satOpen = createString("satOpen");

    public final StringPath sunClose = createString("sunClose");

    public final StringPath sunOpen = createString("sunOpen");

    public final StringPath tel = createString("tel");

    public final StringPath type = createString("type");

    public final StringPath weekdayClose = createString("weekdayClose");

    public final StringPath weekdayOpen = createString("weekdayOpen");

    public QParking(String variable) {
        this(Parking.class, forVariable(variable), INITS);
    }

    public QParking(Path<? extends Parking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParking(PathMetadata metadata, PathInits inits) {
        this(Parking.class, metadata, inits);
    }

    public QParking(Class<? extends Parking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.member = inits.isInitialized("member") ? new teamparkinglot.parkinggo.member.entity.QMember(forProperty("member")) : null;
    }

}

