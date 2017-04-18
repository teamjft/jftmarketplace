package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductEntitlement is a Querydsl query type for ProductEntitlement
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductEntitlement extends EntityPathBase<ProductEntitlement> {

    private static final long serialVersionUID = -2134631406L;

    public static final QProductEntitlement productEntitlement = new QProductEntitlement("productEntitlement");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    public final StringPath activePaymentinstrument = createString("activePaymentinstrument");

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath uuid = createString("uuid");

    public QProductEntitlement(String variable) {
        super(ProductEntitlement.class, forVariable(variable));
    }

    public QProductEntitlement(Path<? extends ProductEntitlement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductEntitlement(PathMetadata<?> metadata) {
        super(ProductEntitlement.class, metadata);
    }

}

