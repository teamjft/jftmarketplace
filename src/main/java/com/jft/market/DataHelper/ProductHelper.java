package com.jft.market.DataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ProductHelper {

	public static <T> void validate(T t) {
		List<ErrorWS> errors = new ArrayList<>();
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(t);

		violations.forEach(violation -> errors.add(new ErrorWS(violation.getPropertyPath(), violation.getMessage())));

		if (!errors.isEmpty()) {
			Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
			ErrorCollectionWS collection = new ErrorCollectionWS();
			errors.stream().forEach(collection::add);
			throw new WebApplicationException(rb.entity(collection).build());
		}
	}
}
