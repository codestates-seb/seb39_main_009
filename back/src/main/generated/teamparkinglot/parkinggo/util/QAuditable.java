package teamparkinglot.parkinggo.util;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import teamparkinglot.parkinggo.advice.util.Auditable;


/**
 * QAuditable is a Querydsl query type for Auditable
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditable extends EntityPathBase<Auditable> {

    private static final long serialVersionUID = 644918767L;

    public static final QAuditable auditable = new QAuditable("auditable");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public QAuditable(String variable) {
        super(Auditable.class, forVariable(variable));
    }

    public QAuditable(Path<? extends Auditable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditable(PathMetadata metadata) {
        super(Auditable.class, metadata);
    }

}

