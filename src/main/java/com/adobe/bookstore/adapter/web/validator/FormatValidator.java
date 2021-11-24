package com.adobe.bookstore.adapter.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormatValidator implements ConstraintValidator<FormatConstraint, String> {
	
	private static final String CSV = "CSV";
	private static final String JSON = "JSON";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.equals(CSV) || value.equals(JSON);
	}

}
