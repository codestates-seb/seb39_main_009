package teamparkinglot.parkinggo.member.uuid;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUuid is a Querydsl query type for Uuid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUuid extends EntityPathBase<Uuid> {

    private static final long serialVersionUID = 216698784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUuid uuid1 = new QUuid("uuid1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final teamparkinglot.parkinggo.member.entity.QMember member;

    public final StringPath uuid = createString("uuid");

    public QUuid(String variable) {
        this(Uuid.class, forVariable(variable), INITS);
    }

    public QUuid(Path<? extends Uuid> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUuid(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUuid(PathMetadata metadata, PathInits inits) {
        this(Uuid.class, metadata, inits);
    }

    public QUuid(Class<? extends Uuid> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new teamparkinglot.parkinggo.member.entity.QMember(forProperty("member")) : null;
    }

}

