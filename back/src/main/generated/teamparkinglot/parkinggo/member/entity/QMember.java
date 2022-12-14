package teamparkinglot.parkinggo.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -938451049L;

    public static final QMember member = new QMember("member1");

    public final StringPath carNumber = createString("carNumber");

    public final StringPath email = createString("email");

    public final BooleanPath eventAgmt = createBoolean("eventAgmt");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    public final BooleanPath psInfoAgmt = createBoolean("psInfoAgmt");

    public final ListPath<teamparkinglot.parkinggo.reservation.entity.Reservation, teamparkinglot.parkinggo.reservation.entity.QReservation> reservation = this.<teamparkinglot.parkinggo.reservation.entity.Reservation, teamparkinglot.parkinggo.reservation.entity.QReservation>createList("reservation", teamparkinglot.parkinggo.reservation.entity.Reservation.class, teamparkinglot.parkinggo.reservation.entity.QReservation.class, PathInits.DIRECT2);

    public final EnumPath<MemberRole> role = createEnum("role", MemberRole.class);

    public final BooleanPath svcUseAgmt = createBoolean("svcUseAgmt");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

