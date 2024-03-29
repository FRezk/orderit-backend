package com.rezk.orderit.mc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}
	
	public void addError(String fieldName, String fieldValue) {
		errors.add(new FieldMessage(fieldName, fieldValue));
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldMessage> list) {
		this.errors = list;
	}

}
