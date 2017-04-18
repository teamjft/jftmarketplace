package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 968785439L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    public final QCustomer customer;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath fname = createString("fname");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final StringPath lname = createString("lname");

    public final StringPath password = createString("password");

    public final NumberPath<Long> phone = createNumber("phone", Long.class);

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath uuid = createString("uuid");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

