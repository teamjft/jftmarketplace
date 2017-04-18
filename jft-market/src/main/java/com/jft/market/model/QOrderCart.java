package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderCart is a Querydsl query type for OrderCart
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderCart extends EntityPathBase<OrderCart> {

    private static final long serialVersionUID = 1046208954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderCart orderCart = new QOrderCart("orderCart");

    public final QCustomer customer;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final SetPath<Product, QProduct> products = this.<Product, QProduct>createSet("products", Product.class, QProduct.class, PathInits.DIRECT2);

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
    }

}

