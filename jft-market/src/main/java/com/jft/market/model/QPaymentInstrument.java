package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPaymentInstrument is a Querydsl query type for PaymentInstrument
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPaymentInstrument extends EntityPathBase<PaymentInstrument> {

    private static final long serialVersionUID = 1677523129L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentInstrument paymentInstrument = new QPaymentInstrument("paymentInstrument");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    public final NumberPath<Long> creditCartNumber = createNumber("creditCartNumber", Long.class);

    public final QCustomer customer;

    public final StringPath cvv = createString("cvv");

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final NumberPath<Long> expirationMonth = createNumber("expirationMonth", Long.class);

    public final NumberPath<Long> expirationYear = createNumber("expirationYear", Long.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final StringPath lastName = createString("lastName");

    public final StringPath nameOnCard = createString("nameOnCard");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath state = createString("state");

    public final EnumPath<com.jft.market.api.PaymnetInstrumentStatus> status = createEnum("status", com.jft.market.api.PaymnetInstrumentStatus.class);

    public final StringPath street1 = createString("street1");

    public final StringPath street2 = createString("street2");

    public final EnumPath<com.jft.market.api.CreditCardTypes> type = createEnum("type", com.jft.market.api.CreditCardTypes.class);

    public final StringPath uuid = createString("uuid");

    public final StringPath zip = createString("zip");

    public QPaymentInstrument(String variable) {
        this(PaymentInstrument.class, forVariable(variable), INITS);
    }

    public QPaymentInstrument(Path<? extends PaymentInstrument> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPaymentInstrument(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPaymentInstrument(PathMetadata<?> metadata, PathInits inits) {
        this(PaymentInstrument.class, metadata, inits);
    }

    public QPaymentInstrument(Class<? extends PaymentInstrument> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

