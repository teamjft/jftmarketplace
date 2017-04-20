package com.jft.market.util;

import com.jft.market.model.QTimestampedFieldObject;
import com.mysema.query.types.Predicate;

public class EntityPredicates {

	public static Predicate isTimestampedFieldEnabledAndNotDeleted(QTimestampedFieldObject timestampedField) {
		return timestampedField.enabled.eq(Boolean.TRUE).and(timestampedField.deleted.eq(Boolean.FALSE));
	}
}
