package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.SetPath;
import com.mysema.query.types.path.StringPath;


/**
 * QOrderCart is a Querydsl query type for OrderCart
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderCart extends EntityPathBase<OrderCart> {

    private static final long serialVersionUID = 1046208954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderCart orderCart = new QOrderCart("orderCart");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    public final QCustomer customer;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final SetPath<Product, QProduct> products = this.<Product, QProduct>createSet("products", Product.class, QProduct.class, PathInits.DIRECT2);

    public final QPurchaseOrder purchaseOrder;

    public final EnumPath<com.jft.market.api.OrderCartStatus> status = createEnum("status", com.jft.market.api.OrderCartStatus.class);

    public final StringPath uuid = createString("uuid");

    public QOrderCart(String variable) {
        this(OrderCart.class, forVariable(variable), INITS);
    }

    public QOrderCart(Path<? extends OrderCart> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderCart(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderCart(PathMetadata<?> metadata, PathInits inits) {
        this(OrderCart.class, metadata, inits);
    }

    public QOrderCart(Class<? extends OrderCart> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.purchaseOrder = inits.isInitialized("purchaseOrder") ? new QPurchaseOrder(forProperty("purchaseOrder"), inits.get("purchaseOrder")) : null;
    }

}

