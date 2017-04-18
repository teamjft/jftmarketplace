package com.jft.market.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1255462373L;

    public static final QProduct product = new QProduct("product");

    public final QTimestampedFieldObject _super = new QTimestampedFieldObject(this);

    public final SetPath<Category, QCategory> categories = this.<Category, QCategory>createSet("categories", Category.class, QCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> createdOn = _super.createdOn;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath description = createString("description");

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final StringPath features = createString("features");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModified = _super.lastModified;

    public final StringPath name = createString("name");

    public final SetPath<OrderCart, QOrderCart> orderCarts = this.<OrderCart, QOrderCart>createSet("orderCarts", OrderCart.class, QOrderCart.class, PathInits.DIRECT2);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final SetPath<PurchaseOrder, QPurchaseOrder> purchaseOrders = this.<PurchaseOrder, QPurchaseOrder>createSet("purchaseOrders", PurchaseOrder.class, QPurchaseOrder.class, PathInits.DIRECT2);

    public final StringPath uuid = createString("uuid");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata<?> metadata) {
        super(Product.class, metadata);
    }

}

