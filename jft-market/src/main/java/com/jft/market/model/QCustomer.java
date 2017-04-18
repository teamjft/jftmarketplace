package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = 1345308722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath email = createString("email");

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final QOrderCart orderCart;

    public final SetPath<PaymentInstrument, QPaymentInstrument> paymentInstrumentList = this.<PaymentInstrument, QPaymentInstrument>createSet("paymentInstrumentList", PaymentInstrument.class, QPaymentInstrument.class, PathInits.DIRECT2);

    public final SetPath<PurchaseOrder, QPurchaseOrder> purchaseOrders = this.<PurchaseOrder, QPurchaseOrder>createSet("purchaseOrders", PurchaseOrder.class, QPurchaseOrder.class, PathInits.DIRECT2);

    public final QUser user;

    public final StringPath uuid = createString("uuid");

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata<?> metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderCart = inits.isInitialized("orderCart") ? new QOrderCart(forProperty("orderCart"), inits.get("orderCart")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

