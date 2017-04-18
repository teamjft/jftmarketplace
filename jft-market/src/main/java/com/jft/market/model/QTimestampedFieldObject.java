package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTimestampedFieldObject is a Querydsl query type for TimestampedFieldObject
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QTimestampedFieldObject extends EntityPathBase<TimestampedFieldObject> {

    private static final long serialVersionUID = 53595832L;

    public static final QTimestampedFieldObject timestampedFieldObject = new QTimestampedFieldObject("timestampedFieldObject");

    public final DateTimePath<java.util.Date> createdOn = createDateTime("createdOn", java.util.Date.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final BooleanPath enabled = createBoolean("enabled");

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public QTimestampedFieldObject(String variable) {
        super(TimestampedFieldObject.class, forVariable(variable));
    }

    public QTimestampedFieldObject(Path<? extends TimestampedFieldObject> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimestampedFieldObject(PathMetadata<?> metadata) {
        super(TimestampedFieldObject.class, metadata);
    }

}

